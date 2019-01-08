package com.demo.api.user.service.impl;

import com.demo.api.common.domain.SystemInfo;
import com.demo.api.common.exception.PreRegistFailException;
import com.demo.api.common.exception.RegistFailException;
import com.demo.api.common.exception.VerifyCodeException;
import com.demo.api.common.exception.WechatLoginException;
import com.demo.api.common.service.JwtUtils;
import com.demo.api.common.service.RedisOperator;
import com.demo.api.mapper.PropertyOwnerMapper;
import com.demo.api.mapper.UserMapper;
import com.demo.api.shortmessage.ShortMessageService;
import com.demo.api.user.domain.PropertyOwner;
import com.demo.api.user.domain.User;
import com.demo.api.user.service.UserService;
import com.demo.api.user.vo.LoginUserInfo;
import com.demo.api.user.vo.ReqForRegist;
import com.demo.api.user.vo.ReqForVerifyCode;
import com.demo.api.user.vo.ReqForWechatLogin;
import com.demo.api.wechat.domain.RepForWechatSession;
import com.demo.api.wechat.service.WechatService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lye
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PropertyOwnerMapper propertyOwnerMapper;
    @Autowired
    private WechatService wechatService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisOperator redisOperator;
    @Autowired
    private ShortMessageService shortMessageService;
    @Autowired
    private SystemInfo systemInfo;

    @Override
    public LoginUserInfo wechatLogin(ReqForWechatLogin reqForWechatLogin) throws WechatLoginException, PreRegistFailException {
        RepForWechatSession repForWechatSession = wechatService.getWechatInfoByCode(reqForWechatLogin.getCode());
        String openId = repForWechatSession.getOpenId();
        if (StringUtils.isBlank(openId)) {
            throw new WechatLoginException("获取微信授权失败");
        }
        User user = userMapper.getUserByOpenId(openId);
        if (user != null) {
            return login(user);
        } else {
            user = new User();
            user.setWechatId(openId);
            int insertCount = userMapper.insert(user);
            if (insertCount == 0) {
                throw new PreRegistFailException("获取微信授权失败");
            }
            return login(user);
        }
    }

    @Override
    public void verifyCode(ReqForVerifyCode reqForVerifyCode) throws VerifyCodeException {
        String mobile = reqForVerifyCode.getMobile();
        Integer mobileVerifyCodeLimit = redisOperator.getMobileVerifyCodeLimit(mobile);
        Integer limitTimes = systemInfo.getShortMessage().getLimitTimes();
        if (limitTimes == null) {
            limitTimes = 5;
        }
        if (mobileVerifyCodeLimit >= limitTimes) {
            throw new VerifyCodeException("验证码获取已达到最大次数，请明天再试");
        }
        String verifyCode = RandomStringUtils.randomNumeric(6);
        String sendMessageResult = shortMessageService.sendMessage(mobile, systemInfo.getShortMessage().getVerifyCodeTemplate().replace("%验证码%", verifyCode));
        redisOperator.setVerifyCodeByMobile(mobile, verifyCode);
        redisOperator.increaseMobileVerifyCodeSentCount(mobile);
        LOGGER.info("手机号：{} 验证码：{} 发送结果：{}", mobile, verifyCode, sendMessageResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoginUserInfo regist(ReqForRegist reqForRegist) throws RegistFailException {
        LoginUserInfo loginUserInfo = (LoginUserInfo) SecurityUtils.getSubject().getPrincipal();
        if (loginUserInfo.getIsActived()) {
            throw new RegistFailException("您已补充个人信息，请不要重复操作");
        }
        String mobile = reqForRegist.getMobile();
        String RealVerifyCode = redisOperator.getVerifyCodeByMobile(mobile);
        if (StringUtils.isBlank(RealVerifyCode)) {
            throw new RegistFailException("验证码过期，请重新获取");
        }
        if (!RealVerifyCode.equalsIgnoreCase(reqForRegist.getVerifyCode())) {
            throw new RegistFailException("验证码错误，请重新输入");
        }
        redisOperator.delVerifyCodeByMobile(mobile);
        User user = userMapper.getUserByOpenId(loginUserInfo.getWechatId());
        user.setIsActived(true);
        int updatedUserCount = userMapper.updateById(user);
        if (updatedUserCount == 0) {
            throw new RegistFailException("补充个人信息失败，请稍后重试");
        }
        PropertyOwner propertyOwner = new PropertyOwner();
        propertyOwner.setBelongAccount(user.getId());
        propertyOwner.setName(reqForRegist.getName());
        propertyOwner.setMobile(mobile);
        propertyOwner.setCertificate(reqForRegist.getCertificateType());
        propertyOwner.setIdCard(reqForRegist.getCertificateId());
        propertyOwner.setType(PropertyOwner.OWNER_TYPE_USER_INFO);
        int insertUserInfoCount = propertyOwnerMapper.insert(propertyOwner);
        if (insertUserInfoCount == 0) {
            throw new RegistFailException("补充个人信息失败，请稍后重试");
        }
        return login(user);
    }

    private LoginUserInfo login(User user) {
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setId(user.getId());
        loginUserInfo.setNickName(user.getNickName());
        loginUserInfo.setIsActived(user.getIsActived());
        loginUserInfo.setLastLoginTime(user.getLastLoginTime());
        String openId = user.getWechatId();
        String token = jwtUtils.createToken(openId);
        loginUserInfo.setToken(token);
        redisOperator.setLoginUserInfoByOpenId(openId, loginUserInfo);
        return loginUserInfo;
    }
}
