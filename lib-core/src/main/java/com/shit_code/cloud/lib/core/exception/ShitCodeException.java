package com.shit_code.cloud.lib.core.exception;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
public class ShitCodeException extends RuntimeException {

    public ShitCodeException() {
        super();
    }

    public ShitCodeException(String message) {
        super(message);
    }

    public ShitCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShitCodeException(Throwable cause) {
        super(cause);
    }

    protected ShitCodeException(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
