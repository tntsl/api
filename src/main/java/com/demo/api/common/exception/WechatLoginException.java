package com.demo.api.common.exception;

/**
 * @author Lye
 */
public class WechatLoginException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 4932724177340219817L;

    public WechatLoginException(String message) {
        super(message);
    }
}
