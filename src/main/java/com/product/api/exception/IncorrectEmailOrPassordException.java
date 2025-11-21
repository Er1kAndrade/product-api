package com.product.api.exception;

public class IncorrectEmailOrPassordException extends RuntimeException{
    public IncorrectEmailOrPassordException(String message) {
        super(message);
    }
}
