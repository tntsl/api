package com.demo.api.user.controller;

import com.demo.api.common.domain.Result;
import com.demo.api.common.exception.PreRegistFailException;
import com.demo.api.common.exception.RegistFailException;
import com.demo.api.common.exception.SendVerifyCodeFailException;
import com.demo.api.common.exception.WechatLoginException;
import com.demo.api.common.util.ValidResultUtils;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public Result<LoginUserInfo> wechatLogin(@RequestBody @Valid ReqForWechatLogin reqForWechatLogin, BindingResult validResult) {
        if (validResult.hasErrors()) {
            return new Result<>().set400().setMessage(ValidResultUtils.resultsToString(validResult));
        }
        try {
            LoginUserInfo loginUserInfo = userService.wechatLogin(reqForWechatLogin);
            return new Result().set200().setData(loginUserInfo);
        } catch (WechatLoginException e) {
            return new Result().set500().setMessage(e.getMessage());
        } catch (PreRegistFailException e) {
            return new Result().set500().setMessage(e.getMessage());
        }
    }

    @ApiOperation("短信验证码")
    @RequiresUser
    @PostMapping("verifyCode")
    public Result verifyCode(@RequestBody @Valid ReqForVerifyCode reqForVerifyCode, BindingResult validResult) {
        if (validResult.hasErrors()) {
            return new Result<>().set400().setMessage(ValidResultUtils.resultsToString(validResult));
        }
        try {
            userService.verifyCode(reqForVerifyCode);
            return new Result().set200().setMessage("验证码短信已发送");
        } catch (SendVerifyCodeFailException e) {
            return new Result().set500().setMessage(e.getMessage());
        }
    }

    @ApiOperation("补充信息")
    @RequiresUser
    @PostMapping("regist")
    public Result<LoginUserInfo> regist(@RequestBody @Valid ReqForRegist reqForRegist, BindingResult validResult) {
        if (validResult.hasErrors()) {
            return new Result<>().set400().setMessage(ValidResultUtils.resultsToString(validResult));
        }
        try {
            LoginUserInfo loginUserInfo = userService.regist(reqForRegist);
            return new Result().set200().setMessage("补充信息成功").setData(loginUserInfo);
        } catch (RegistFailException e) {
            return new Result().set500().setMessage(e.getMessage());
        }
    }
}
