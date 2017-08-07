package com.mokazu.exception;

import org.apache.shiro.authc.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {
	private static final long serialVersionUID = 1L;
	//异常信息
    public String message;

    public CustomAuthenticationException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
