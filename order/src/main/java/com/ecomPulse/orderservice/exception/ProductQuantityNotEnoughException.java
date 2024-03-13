package com.ecomPulse.orderservice.exception;

public class ProductQuantityNotEnoughException extends RuntimeException{
    public ProductQuantityNotEnoughException(String message) {
        super(message);
    }

    public ProductQuantityNotEnoughException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductQuantityNotEnoughException(Throwable cause) {
        super(cause);
    }
}
