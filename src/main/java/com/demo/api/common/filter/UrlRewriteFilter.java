package com.demo.api.common.filter;

import com.demo.api.common.domain.RequestParamWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lye
 */
public class UrlRewriteFilter extends OncePerRequestFilter {

    private Pattern pattern = Pattern.compile("^/v(\\d{1,}\\.\\d{1,})(/.*)");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RequestParamWrapper requestParamWrapper = new RequestParamWrapper(request);
        String requestURI = requestParamWrapper.getRequestURI();
        Matcher m = pattern.matcher(requestURI);
        if (m.matches()) {
            String v = m.group(1);
            String url = m.group(2);
            requestParamWrapper.addParameter("v", v);
            requestParamWrapper.setRequestUri(url);
            StringBuffer requestURL = requestParamWrapper.getRequestURL();
            requestParamWrapper.setRequestUrl(new StringBuffer(requestURL.toString().replace("/v" + v, "")));
        }
        filterChain.doFilter(requestParamWrapper, response);
    }
}
