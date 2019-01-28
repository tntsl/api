package com.demo.api.common.controller;

import com.demo.api.common.domain.Result;
import com.demo.api.common.util.ValidResultUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Lye
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕捉其他所有异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(Exception e) {
        if (e instanceof UnauthorizedException || e instanceof AuthorizationException) {
            return new Result().set403();
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            return new Result().set400().setMessage(ValidResultUtils.resultsToString(bindingResult));
        } else {
            LOGGER.error(e.getMessage(), e);
            return new Result().set500().setMessage("请求发生错误，请稍后重试");
        }
    }

}
