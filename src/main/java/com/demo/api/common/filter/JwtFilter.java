package com.demo.api.common.filter;

import com.demo.api.common.domain.JwtToken;
import com.demo.api.common.domain.Result;
import com.demo.api.common.domain.SystemInfo;
import com.demo.api.common.util.GsonUtils;
import org.apache.commons.lang3.StringUtils;
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
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader(systemInfo.getTokenHeader());
        if (StringUtils.isNotBlank(token)) {
            try {
                return executeLogin(request, response);
            } catch (Exception e) {
                LOGGER.trace(e.getMessage(), e);
                sendErrorMessage(response, e.getMessage());
            }
        }
        sendErrorMessage(response, "token无效");
        return false;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader(systemInfo.getTokenHeader());
        if (StringUtils.isBlank(token)) {
            return false;
        }
        JwtToken jwtToken = new JwtToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String token = httpServletRequest.getHeader(systemInfo.getTokenHeader());
        if (StringUtils.isNotBlank(token)) {
            return new JwtToken(token);
        }
        sendErrorMessage(response, "token无效");
        return null;
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
            Result result = new Result().set401().setMessage(message);
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
