package com.demo.api.wechat.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author Lye
 */
@Data
public class RepForWechatSession {

    @SerializedName("session_key")
    private String sessionKey;
    @SerializedName("openid")
    private String openId;
    @SerializedName("errcode")
    private Integer errCode;
    @SerializedName("errmsg")
    private String errMsg;
}
