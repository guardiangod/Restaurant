package com.ryan.app.common;

public class CommonException extends Exception {

	private static final long serialVersionUID = 1180787028446130733L;

	public CommonException(String message) {
		super(message);
	}
	
	public CommonException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public CommonException(Throwable cause) {
        super(cause);
    }
	
	protected CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
