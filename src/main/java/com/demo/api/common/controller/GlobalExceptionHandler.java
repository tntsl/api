package com.demo.api.common.controller;

import com.demo.api.common.domain.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lye
 * 统一捕捉shiro的异常，返回给前台一个json信息，前台根据这个信息显示对应的提示，或者做页面的跳转。
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕捉其他所有异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(HttpServletRequest request, Throwable ex) {
        LOGGER.error(ex.getMessage(), ex);
        if (ex instanceof UnauthorizedException) {
            return new Result().set403();
        }
        return Result.fail().setMessage("请求发生错误，请稍后重试");
    }

}
