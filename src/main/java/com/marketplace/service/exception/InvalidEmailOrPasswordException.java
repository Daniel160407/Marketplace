package com.marketplace.service.exception;

public class InvalidEmailOrPasswordException extends RuntimeException {
    public InvalidEmailOrPasswordException() {
        super("Invalid email or password");
    }
}
