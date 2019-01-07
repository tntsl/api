package com.demo.api.user.mapper;

import com.demo.api.ApplicationTests;
import com.demo.api.common.util.GsonUtils;
import com.demo.api.user.domain.User;
import com.demo.api.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends ApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectById() {
        User user = userMapper.selectById(29);
        System.out.println(GsonUtils.toJson(user));
    }
}
