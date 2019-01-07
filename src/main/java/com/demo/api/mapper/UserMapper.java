package com.demo.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.api.user.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Lye
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from sys_accounts where wechat_id=#{openId}")
    public User getUserByOpenId(String openId);
}
