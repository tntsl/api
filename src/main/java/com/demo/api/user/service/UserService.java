package com.demo.api.user.service;

import com.demo.api.common.exception.PreRegistFailException;
import com.demo.api.common.exception.RegistFailException;
import com.demo.api.common.exception.SendVerifyCodeFailException;
import com.demo.api.common.exception.WechatLoginException;
import com.demo.api.user.vo.LoginUserInfo;
import com.demo.api.user.vo.ReqForRegist;
import com.demo.api.user.vo.ReqForVerifyCode;
import com.demo.api.user.vo.ReqForWechatLogin;

/**
 * @author Lye
 */
public interface UserService {
    LoginUserInfo wechatLogin(ReqForWechatLogin reqForWechatLogin) throws WechatLoginException, PreRegistFailException;

    void verifyCode(ReqForVerifyCode reqForVerifyCode) throws SendVerifyCodeFailException;

    LoginUserInfo regist(ReqForRegist reqForRegist) throws RegistFailException;
}
