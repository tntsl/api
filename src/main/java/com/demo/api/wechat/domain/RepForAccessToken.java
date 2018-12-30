package com.demo.api.wechat.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author Lye
 */
@Data
public class RepForAccessToken {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private Long expiresIn;
}
