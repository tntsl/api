package com.demo.api.wechat.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Map;

/**
 * @author Lye
 */
@Data
public class ReqForSendMessageByTemplate {
    private String touser;
    @SerializedName("template_id")
    private String templateId;
    private String page;
    @SerializedName("form_id")
    private String formId;
    private Map<String, Map<String, String>> data;
    /**
     * 需要放大的关键字
     */
    @SerializedName("emphasis_keyword")
    private String emphasisKeyword;

}
