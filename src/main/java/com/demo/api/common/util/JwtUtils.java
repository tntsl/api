package com.demo.api.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.api.common.domain.SystemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Lye
 */
@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    private SystemInfo systemInfo;

    /**
     * 解密Jwt token
     *
     * @param token
     * @return
     * @throws UnsupportedEncodingException
     */
    public DecodedJWT decodeJwtToken(String token) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(systemInfo.getTokenSecurityKey());
        JWTVerifier jwtVerifier = JWT.require(algorithm)
            .withIssuer(systemInfo.getTokenIssuer())
            .withAudience(systemInfo.getTokenAudience())
            .build();
        return jwtVerifier.verify(token);
    }

    /**
     * 校验Jwt token
     *
     * @param token
     * @return
     */
    public Boolean verify(String token) {
        try {
            decodeJwtToken(token);
            return true;
        } catch (Exception e) {
            LOGGER.trace(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 创建Jwt token
     *
     * @param tokenId
     * @return
     */
    public String createToken(String tokenId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(systemInfo.getTokenSecurityKey());
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            calendar.add(Calendar.HOUR, systemInfo.getTokenExpire());
            Date exp = calendar.getTime();
            return JWT.create()
                .withIssuer(systemInfo.getTokenIssuer())
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .withAudience(systemInfo.getTokenAudience())
                .withJWTId(tokenId)
                .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
