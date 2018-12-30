package com.demo.api.common.domain;

import lombok.Data;

/**
 * @author Lye
 */
@Data
public class ShortMessage {
    private String sn;
    private String pwd;
    private String verifyCodeTemplate;
    private Integer limitTimes;
    private String hookMobile;
}
