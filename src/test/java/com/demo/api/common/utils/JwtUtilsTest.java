package com.demo.api.common.utils;

import com.demo.api.ApplicationTests;
import com.demo.api.common.util.JwtUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtUtilsTest extends ApplicationTests {
    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void generateToken() {
        String token = jwtUtils.createToken("test");
        System.out.println(token);
    }
}
