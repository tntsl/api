package com.demo.api.common.util;

import com.demo.api.common.GlobalConstParam;
import com.demo.api.common.domain.JwtToken;
import com.demo.api.user.vo.LoginUserInfo;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;

/**
 * @author Lye
 */
public class JwtRealm extends AuthorizingRealm {
    @Autowired
    private RedisOperator redisOperator;
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public String getName() {
        return JwtRealm.class.getSimpleName();
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = jwtToken.getToken();
        if (!jwtUtils.verify(token)) {
            throw new AuthenticationException("token格式有误");
        }
        String openId = null;
        try {
            openId = jwtUtils.decodeJwtToken(token).getId();
        } catch (UnsupportedEncodingException e) {
            throw new AuthenticationException("token格式有误");
        }
        if (StringUtils.isBlank(openId)) {
            throw new AuthenticationException("token信息有误");
        }
        String accountJson = redisOperator.get(GlobalConstParam.WECHAT_USER.concat("_").concat(openId));
        if (StringUtils.isBlank(accountJson)) {
            throw new AuthenticationException("登录信息已过期，请重新登录");
        }
        LoginUserInfo loginUserInfo = new Gson().fromJson(accountJson, LoginUserInfo.class);
        loginUserInfo.setWechatId(openId);
        return new SimpleAuthenticationInfo(loginUserInfo, jwtToken.getCredentials(), getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        LoginUserInfo loginUserInfo = (LoginUserInfo) principalCollection.getPrimaryPrincipal();
        authorizationInfo.setStringPermissions(loginUserInfo.getPermissions());
        return authorizationInfo;
    }
}
