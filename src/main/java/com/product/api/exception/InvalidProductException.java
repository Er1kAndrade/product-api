package com.product.api.exception;

public class InvalidProductException  extends RuntimeException {
    public InvalidProductException(String message) {
        super(message);
    }
}
