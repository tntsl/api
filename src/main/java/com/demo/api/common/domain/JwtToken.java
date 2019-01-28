package com.demo.api.common.domain;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Lye
 */
@Data
public class JwtToken implements AuthenticationToken {
    /**
     *
     */
    private static final long serialVersionUID = -5070522119672582232L;
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
