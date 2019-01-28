package com.demo.api.common.domain;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @author Lye
 */
public class RequestParamWrapper extends HttpServletRequestWrapper {
    private final HashMap<String, String[]> overridenParameters = Maps.newHashMap();
    private String requestUri = "";
    private StringBuffer requestUrl = new StringBuffer("");

    public RequestParamWrapper(HttpServletRequest request) {
        super(request);
    }

    public void addParameter(String key, String... values) {
        overridenParameters.put(key, values);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        if (overridenParameters != null) {
            List<String> keys = Collections.list(super.getParameterNames());
            keys.addAll(overridenParameters.keySet());
            return Collections.enumeration(keys);
        }
        return super.getParameterNames();
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        if (overridenParameters != null) {
            Map<String, String[]> superMap = super.getParameterMap();
            //superMap is an unmodifiable map, hence creating a new one.
            Map<String, String[]> overriddenMap = new HashMap<>(superMap.size() + overridenParameters.size());
            overriddenMap.putAll(superMap);
            overriddenMap.putAll(overridenParameters);
            return overriddenMap;
        }
        return super.getParameterMap();
    }

    @Override
    public String[] getParameterValues(String s) {
        if (overridenParameters != null && overridenParameters.containsKey(s)) {
            return overridenParameters.get(s);
        }
        return super.getParameterValues(s);
    }

    @Override
    public String getParameter(String s) {
        if (overridenParameters != null && overridenParameters.containsKey(s)) {
            String[] values = overridenParameters.get(s);
            if (values == null || values.length == 0) {
                return null;
            } else {
                return values[0];
            }
        }
        return super.getParameter(s);
    }

    @Override
    public String getRequestURI() {
        if (StringUtils.isNotBlank(requestUri)) {
            return requestUri;
        }
        return super.getRequestURI();
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    @Override
    public StringBuffer getRequestURL() {
        if (StringUtils.isNotBlank(requestUrl.toString())) {
            return requestUrl;
        }
        return super.getRequestURL();
    }

    public void setRequestUrl(StringBuffer requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public String getServletPath() {
        if (StringUtils.isNotBlank(requestUri)) {
            return requestUri;
        }
        return super.getServletPath();
    }
}
