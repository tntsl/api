package com.demo.api.user.service;

import com.demo.api.common.exception.LoginFailException;
import com.demo.api.user.domain.ReqForRegist;
import com.demo.api.user.domain.ReqForVerifyCode;
import com.demo.api.user.domain.ReqForWechatLogin;
import com.demo.api.common.exception.PreRegistFailException;
import com.demo.api.common.exception.RegistFailException;
import com.demo.api.user.domain.LoginUserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lye
 */
public interface UserService {
    public void sendVerifyCodeMessage(ReqForVerifyCode reqForVerifyCode) throws Exception;

    public LoginUserInfo regist(ReqForRegist reqForRegist) throws RegistFailException;

    public LoginUserInfo wechatLogin(ReqForWechatLogin reqForWechatLogin, HttpServletRequest request) throws LoginFailException, PreRegistFailException;
}
