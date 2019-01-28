package com.demo.api.common.exception;

/**
 * @author Lye
 */
public class SendVerifyCodeFailException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -6136434768471691732L;

    public SendVerifyCodeFailException(String message) {
        super(message);
    }
}
