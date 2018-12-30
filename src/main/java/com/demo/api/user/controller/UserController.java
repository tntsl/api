package com.demo.api.user.controller;

import com.demo.api.user.domain.ReqForRegist;
import com.demo.api.user.domain.ReqForVerifyCode;
import com.demo.api.user.domain.ReqForWechatLogin;
import com.demo.api.user.service.UserService;
import com.demo.api.common.domain.Result;
import com.demo.api.common.exception.RegistFailException;
import com.demo.api.common.util.ValidResultUtils;
import com.demo.api.user.domain.LoginUserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Lye
 */
@Api(tags = "用户登录注册")
@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取手机号验证码")
    @RequiresUser
    @PostMapping(value = "verifyCode", params = {"v=1.0"})
    public Result verifyCode(@ApiParam(required = true, name = "请求参数") @RequestBody @Valid ReqForVerifyCode reqForVerifyCode, BindingResult validResult) {
        if (validResult.hasErrors()) {
            return new Result().failure().setMessage(ValidResultUtils.resultsToString(validResult));
        }
        try {
            userService.sendVerifyCodeMessage(reqForVerifyCode);
        } catch (Exception e) {
            return new Result().set500().setMessage(e.getMessage());
        }
        return new Result().success();
    }

    /**
     * 用户手机号、手机验证码注册
     *
     * @param reqForRegist
     * @param validResult
     * @return
     */
    @ApiOperation(value = "用户注册")
    @RequiresUser
    @PostMapping(value = "regist", params = {"v=1.0"})
    public Result<LoginUserInfo> regist(@ApiParam(required = true, name = "请求参数") @RequestBody @Valid ReqForRegist reqForRegist, BindingResult validResult, HttpServletRequest request) {
        if (validResult.hasErrors()) {
            return new Result().failure().setMessage(ValidResultUtils.resultsToString(validResult));
        }
        try {
            LoginUserInfo loginUserInfo = userService.regist(reqForRegist);
            return new Result<LoginUserInfo>().success().setData(loginUserInfo);
        } catch (RegistFailException e) {
            return new Result().set500().setMessage(e.getMessage());
        }
    }

    /**
     * 微信登录
     *
     * @param reqForWechatLogin
     * @return
     */
    @ApiOperation(value = "微信Code登录")
    @PostMapping(value = "wechatLogin", params = {"v=1.0"})
    public Result<LoginUserInfo> wechatLogin(@ApiParam(required = true, name = "微信小程序的code") @RequestBody @Valid ReqForWechatLogin reqForWechatLogin, BindingResult validResult, HttpServletRequest request) {
        if (validResult.hasErrors()) {
            return new Result().setCode(400).setMessage(ValidResultUtils.resultsToString(validResult));
        }
        try {
            LoginUserInfo result = userService.wechatLogin(reqForWechatLogin, request);
            return new Result().success().setData(result);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new Result().setCode(500).setMessage(e.getMessage());
        }
    }
}
