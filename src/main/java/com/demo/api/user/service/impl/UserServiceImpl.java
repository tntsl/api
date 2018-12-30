package com.demo.api.user.service.impl;

import com.demo.api.common.exception.LoginFailException;
import com.demo.api.user.domain.ReqForRegist;
import com.demo.api.user.domain.ReqForVerifyCode;
import com.demo.api.user.domain.ReqForWechatLogin;
import com.demo.api.user.service.UserService;
import com.demo.api.wechat.service.WechatService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.TxConfig;
import com.demo.api.common.GlobalConstParam;
import com.demo.api.common.domain.ShortMessage;
import com.demo.api.common.domain.SystemInfo;
import com.demo.api.common.exception.PreRegistFailException;
import com.demo.api.common.exception.RegistFailException;
import com.demo.api.common.trasaction.JFinalTx;
import com.demo.api.common.util.GsonUtils;
import com.demo.api.common.util.JwtUtils;
import com.demo.api.common.util.MobileVerifyCodeUtil;
import com.demo.api.common.util.RedisOperator;
import com.demo.api.jfinal.model.DtPropertyOwners;
import com.demo.api.jfinal.model.SysAccounts;
import com.demo.api.jfinal.model.SysLoginLog;
import com.demo.api.shortmessage.ShortMessageService;
import com.demo.api.user.domain.LoginUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author Lye
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private SystemInfo systemInfo;
    @Autowired
    private RedisOperator redisOperator;
    @Autowired
    private ShortMessageService shortMessageService;
    @Autowired
    private WechatService wechatService;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void sendVerifyCodeMessage(ReqForVerifyCode reqForVerifyCode) throws Exception {
        String mobile = reqForVerifyCode.getMobile();
        ShortMessage shortMessage = systemInfo.getShortMessage();
        String hookMobile = shortMessage.getHookMobile();
        if (StringUtils.isNotBlank(hookMobile)) {
            mobile = hookMobile;
        }
        String sendMessageTimesString = redisOperator.hget(GlobalConstParam.MOBILE_REGIST_VERIFYCODE_LIMIT, mobile);
        Integer sendMessageTimes = 0;
        if (StringUtils.isNotBlank(sendMessageTimesString)) {
            sendMessageTimes = Integer.valueOf(sendMessageTimesString);
        }
        if (sendMessageTimes < shortMessage.getLimitTimes()) {
            //TODO 此处存在bug，预留为测试使用
            String verifyCodeKey = GlobalConstParam.MOBILE_REGIST_VERIFYCODE.concat("_").concat(reqForVerifyCode.getMobile());
            String random = MobileVerifyCodeUtil.createRandom(true, 6);
            redisOperator.set(verifyCodeKey, random);
            redisOperator.expire(verifyCodeKey, 60L * 5);

            String sendMessageResult = shortMessageService.sendMessage(mobile, shortMessage.getVerifyCodeTemplate().replace("%验证码%", random));
            LOGGER.info("发送短信验证码结果：{}", sendMessageResult);
            sendMessageTimes++;
            //判断之前该hash是否存在，不存在则在设置完值之后设置过期事件
            Boolean exists = redisOperator.exists(GlobalConstParam.MOBILE_REGIST_VERIFYCODE_LIMIT);
            redisOperator.hset(GlobalConstParam.MOBILE_REGIST_VERIFYCODE_LIMIT, mobile, String.valueOf(sendMessageTimes));
            if (!exists) {
                Calendar calendar = Calendar.getInstance();
                long currentTimeInMillis = calendar.getTimeInMillis();
                calendar.set(Calendar.HOUR, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                long expireTimeInMillis = calendar.getTimeInMillis();
                redisOperator.expire(GlobalConstParam.MOBILE_REGIST_VERIFYCODE_LIMIT, (expireTimeInMillis - currentTimeInMillis) / 1000);
            }
            LOGGER.info("短信验证码：{} 已经被发送到：{}", random, mobile);
        } else {
            throw new Exception("该号码短信接收次数已经达到限额,请明天再试");
        }
    }

    @Override
    @TxConfig("ds1")
    @JFinalTx
    public LoginUserInfo regist(ReqForRegist reqForRegist) throws RegistFailException {
        String openId = (String) SecurityUtils.getSubject().getPrincipal();
        LoginUserInfo loginUserInfo = redisOperator.getLoginUserInfoByOpenId(openId);
        if (loginUserInfo.getIsActived()) {
            throw new RegistFailException("您已注册，请不要重复操作");
        }
        String userVerifyCode = reqForRegist.getVerifyCode();
        if (StringUtils.isBlank(userVerifyCode)) {
            throw new RegistFailException("请输入验证码");
        }
        //TODO 此处存在bug，预留为测试使用
        String verifyCodeKey = GlobalConstParam.MOBILE_REGIST_VERIFYCODE.concat("_").concat(reqForRegist.getMobile());
        //获取并删除redis中验证码
        String verifyCode = redisOperator.get(verifyCodeKey);
        redisOperator.del(verifyCodeKey);
        if (StringUtils.isBlank(verifyCode)) {
            throw new RegistFailException("验证码已过期，请重新获取");
        }
        if (!verifyCode.equalsIgnoreCase(userVerifyCode)) {
            throw new RegistFailException("验证码错误，请重新输入");
        }
        //根据手机号查询用户是否已注册
        SysAccounts accountByAccount = SysAccounts.dao.findFirst("select * from sys_accounts where account=?", reqForRegist.getMobile());
        if (accountByAccount != null) {
            throw new RegistFailException("您已注册，请不要重复操作");
        }
        SysAccounts realAccount = SysAccounts.dao.findFirst("select * from sys_accounts where wechat_id=?", loginUserInfo.getWechatId());
        if (realAccount == null) {
            LOGGER.warn("没有获取到用户预注册信息");
            throw new RegistFailException("注册失败，请稍后重试");
        }
        realAccount.setAccount(reqForRegist.getMobile());
        realAccount.setNickName(reqForRegist.getName());
        realAccount.setIsActived(true);
        realAccount.setActiveTime(new Date());
        boolean accountUpdateFlag = Db.use("ds1").update("sys_accounts", realAccount.toRecord());
        if (!accountUpdateFlag) {
            LOGGER.warn("更新用户预注册信息失败，{}", realAccount.toJson());
            throw new RegistFailException("注册失败，请稍后重试");
        }
        DtPropertyOwners propertyOwners = new DtPropertyOwners();
        propertyOwners.setType(1);
        propertyOwners.setName(reqForRegist.getName());
        propertyOwners.setMobile(reqForRegist.getMobile());
        propertyOwners.setCertificate(reqForRegist.getCertificateType());
        propertyOwners.setIdCard(reqForRegist.getCertificateId());
        propertyOwners.setBelongAccount(realAccount.getId());
        boolean saveFlag = propertyOwners.save();
        if (!saveFlag) {
            LOGGER.warn("保存用户详细资料失败，{}", propertyOwners.toJson());
            throw new RegistFailException("注册失败，请稍后重试");
        }
        return login(realAccount, null);
    }

    @Override
    public LoginUserInfo wechatLogin(ReqForWechatLogin reqForWechatLogin, HttpServletRequest request) throws LoginFailException, PreRegistFailException {
        //调用微信服务端,通过code值得到openid
        Map<String, String> wechatInfo = wechatService.getWechatInfoByCode(reqForWechatLogin.getCode());
        String openid = wechatInfo.get("openid");
        if (StringUtils.isBlank(openid)) {
            throw new LoginFailException("微信授权失败，请重试");
        }
        //根据openid查询账号信息
        SysAccounts account = SysAccounts.dao.findFirst("select * from sys_accounts where wechat_id=?", openid);
        if (account != null) {
            return login(account, request);
        } else {
            account = new SysAccounts();
            account.setWechatId(openid);
            account.setIsActived(false);
            boolean saveFlag = account.save();
            if (!saveFlag) {
                throw new PreRegistFailException("保存用户信息失败");
            }
            return login(account, request);
        }
    }

    private LoginUserInfo login(SysAccounts account, HttpServletRequest request) {
        //获取最后一次登录时间
        Date lastLoginTime = account.getLastLoginTime();
        //修改最后登录时间
        Db.use("ds1").update("update sys_accounts set last_login_time=now() where id=?", account.getId());
        //创建JWT token认证
        String openId = account.getWechatId();
        String token = jwtUtils.createToken(openId);
        //添加返回数据
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setWechatId(openId);
        loginUserInfo.setId(account.getId());
        loginUserInfo.setNickName(account.getNickName());
        loginUserInfo.setLastLoginTime(lastLoginTime);
        loginUserInfo.setToken(token);
        loginUserInfo.setIsActived(account.getIsActived());
        redisOperator.setLoginUserInfoByOpenId(openId, GsonUtils.toJson(loginUserInfo));
        saveLoginLog(account, request);
        //返回前台数据前去掉openId
        loginUserInfo.setWechatId(null);
        return loginUserInfo;
    }

    private void saveLoginLog(SysAccounts account, HttpServletRequest request) {
        try {
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            long timeInMillis = calendar.getTimeInMillis();
            String loginYear = DateFormatUtils.format(now, "yyyy");
            String loginMonth = DateFormatUtils.format(now, "yyyy-MM");
            String loginDate = DateFormatUtils.format(now, "yyyy-MM-dd");
            SysLoginLog loginLog = new SysLoginLog();
            String wechatId = account.getWechatId();
            loginLog.setId(wechatId.concat("_".concat(String.valueOf(timeInMillis))));
            loginLog.setLoginYear(loginYear);
            loginLog.setLoginMonth(loginMonth);
            loginLog.setLoginDate(loginDate);
            loginLog.setAccount(account.getAccount());
            loginLog.setWechatId(wechatId);
            loginLog.setLoginTime(new Date());
            if (request != null) {
                String remoteAddr = request.getHeader("x-forwarded-for");
                if (StringUtils.isNotBlank(remoteAddr)) {
                    loginLog.setLoginIp(remoteAddr);
                }
                String userAgent = request.getHeader("user-agent");
                if (StringUtils.isNotBlank(userAgent)) {
                    loginLog.setUserAgent(userAgent);
                }
            }
            loginLog.save();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
