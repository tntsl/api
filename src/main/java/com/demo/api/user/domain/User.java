package com.demo.api.user.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Lye
 */
@Data
@TableName("sys_accounts")
public class User {
    @TableId
    private Integer id;
    private String wechatId;
    private String nickName;
    private String account;
    private Boolean isActived;
    private Date createTime;
    private Date activeTime;
    private Date lastLoginTime;
}
