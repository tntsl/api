package com.demo.api.common.filter;

import com.demo.api.common.domain.JwtToken;
import com.demo.api.common.domain.Result;
import com.demo.api.common.domain.SystemInfo;
import com.demo.api.common.util.GsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Lye
 */
public class JwtFilter extends AuthenticatingFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    private SystemInfo systemInfo;

    public JwtFilter(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        } else if (isLoginAttempt(request)) {
            return executeLogin(request, response);
        }
        sendErrorMessage(response, "token无效");
        return false;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader(systemInfo.getTokenHeader());
        return new JwtToken(token);
    }

    protected Boolean isLoginAttempt(ServletRequest request) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader(systemInfo.getTokenHeader());
        return StringUtils.isNotBlank(token);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        sendErrorMessage(response, "token无效");
        return false;
    }

    /**
     * 获取账号信息，并设置到request属性
     *
     * @param response
     * @param message
     */
    private void sendErrorMessage(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
            Result<Object> result = new Result<Object>().set401().setMessage(message);
            httpServletResponse.setHeader("content-type", "application/json; charset=utf-8");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(GsonUtils.toJson(result).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


}
