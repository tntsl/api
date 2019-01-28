package com.demo.api.common.exception;

/**
 * @author Lye
 */
public class LoginFailException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 5678699705860187987L;

    public LoginFailException(String message) {
        super(message);
    }
}
