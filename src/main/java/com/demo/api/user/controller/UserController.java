package com.demo.api.user.controller;

import com.demo.api.common.domain.Result;
import com.demo.api.common.exception.PreRegistFailException;
import com.demo.api.common.exception.RegistFailException;
import com.demo.api.common.exception.SendVerifyCodeFailException;
import com.demo.api.common.exception.WechatLoginException;
import com.demo.api.user.service.UserService;
import com.demo.api.user.vo.LoginUserInfo;
import com.demo.api.user.vo.ReqForRegist;
import com.demo.api.user.vo.ReqForVerifyCode;
import com.demo.api.user.vo.ReqForWechatLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lye
 */
@Api(tags = "用户")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("微信登录")
    @RequiresGuest
    @PostMapping("wechatLogin")
    public Result<LoginUserInfo> wechatLogin(@RequestBody @Validated ReqForWechatLogin reqForWechatLogin) {
        try {
            LoginUserInfo loginUserInfo = userService.wechatLogin(reqForWechatLogin);
            return new Result<LoginUserInfo>().set200().setData(loginUserInfo);
        } catch (WechatLoginException | PreRegistFailException e) {
            return new Result<LoginUserInfo>().set500().setMessage(e.getMessage());
        }
    }

    @ApiOperation("短信验证码")
    @RequiresUser
    @PostMapping("verifyCode")
    public Result<Object> verifyCode(@RequestBody @Validated ReqForVerifyCode reqForVerifyCode) {
        try {
            userService.verifyCode(reqForVerifyCode);
            return new Result<Object>().set200().setMessage("验证码短信已发送");
        } catch (SendVerifyCodeFailException e) {
            return new Result<Object>().set500().setMessage(e.getMessage());
        }
    }

    @ApiOperation("补充信息")
    @RequiresUser
    @PostMapping("regist")
    public Result<LoginUserInfo> regist(@RequestBody @Validated ReqForRegist reqForRegist) {
        try {
            LoginUserInfo loginUserInfo = userService.regist(reqForRegist);
            return new Result<LoginUserInfo>().set200().setMessage("补充信息成功").setData(loginUserInfo);
        } catch (RegistFailException e) {
            return new Result<LoginUserInfo>().set500().setMessage(e.getMessage());
        }
    }
}
