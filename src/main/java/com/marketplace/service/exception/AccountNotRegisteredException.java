package com.marketplace.service.exception;

public class AccountNotRegisteredException extends RuntimeException{
    public AccountNotRegisteredException() {
        super("This account is not registered yet");
    }
}
