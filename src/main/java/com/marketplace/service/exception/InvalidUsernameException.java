package com.marketplace.service.exception;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException() {
        super("Your username has invalid syntax");
    }
}
