package com.demo.api.common.aop;

import com.demo.api.common.domain.Result;
import com.demo.api.common.service.RedisOperator;
import com.demo.api.common.util.GsonUtils;
import com.demo.api.common.util.MD5Utils;
import com.demo.api.user.vo.LoginUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Lye
 */
@Component
@Aspect
public class PreventRepeatSubmitAop {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreventRepeatSubmitAop.class);

    @Autowired
    private RedisOperator redisOperator;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    private void pointCut() {

    }

    @Around(value = "pointCut()", argNames = "proceedingJoinPoint")
    private Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        LoginUserInfo loginUserInfo = (LoginUserInfo) SecurityUtils.getSubject().getPrincipal();
        Field field = proceedingJoinPoint.getClass().getDeclaredField("methodInvocation");
        field.setAccessible(true);
        ReflectiveMethodInvocation methodInvocation = (ReflectiveMethodInvocation) field.get(proceedingJoinPoint);
        Method method = methodInvocation.getMethod();
        String content = getRequestBody(proceedingJoinPoint.getArgs(), method.getParameters());
        String currentTimeInMillis = String.valueOf(System.currentTimeMillis());
        String wechatId = currentTimeInMillis;
        if (loginUserInfo != null && StringUtils.isNotBlank(loginUserInfo.getWechatId())) {
            wechatId = loginUserInfo.getWechatId();
        }
        String requestKey = requestURI.concat("_").concat(wechatId).concat("_").concat(MD5Utils.getMD5(content));
        Boolean getExecuteFlag = redisOperator.setNX(requestKey, currentTimeInMillis);
        if (!getExecuteFlag) {
            LOGGER.warn("请求重复，访问路径：{}，参数：{}", requestURI, content);
            Class<?> returnType = method.getReturnType();
            Result result = new Result().set500().setMessage("请不要重复请求");
            if (returnType.equals(String.class)) {
                return GsonUtils.toJsonWithNulls(result);
            } else if (returnType.equals(Result.class)) {
                return result;
            }
            return null;
        }
        redisOperator.expire(requestKey, 60);
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } finally {
            redisOperator.delIfMatch(requestKey, currentTimeInMillis);
        }
        return result;
    }

    /**
     * 根据参数为经过hibernate validate校验进行判断
     *
     * @param args
     * @param parameters
     * @return
     */
    private String getRequestBody(Object[] args, Parameter[] parameters) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            RequestBody[] annotationsByType = parameter.getDeclaredAnnotationsByType(RequestBody.class);
            if (annotationsByType.length > 0) {
                String content = GsonUtils.toJson(args[i]);
                stringBuilder.append(content);
            }
        }
        return stringBuilder.toString();
    }
}
