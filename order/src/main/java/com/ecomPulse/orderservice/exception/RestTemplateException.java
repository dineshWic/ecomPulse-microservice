package com.ecomPulse.orderservice.exception;

public class RestTemplateException extends  RuntimeException{
    public RestTemplateException(String message) {
        super(message);
    }

    public RestTemplateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestTemplateException(Throwable cause) {
        super(cause);
    }
}
