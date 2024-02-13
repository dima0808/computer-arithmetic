package com.civka.calculatordemo.exception;

public class WebUserNotFoundException extends RuntimeException {

    public WebUserNotFoundException(String message) {
        super(message);
    }

    public WebUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebUserNotFoundException(Throwable cause) {
        super(cause);
    }
}
