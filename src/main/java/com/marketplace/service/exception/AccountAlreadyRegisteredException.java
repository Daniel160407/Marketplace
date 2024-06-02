package com.marketplace.service.exception;

public class AccountAlreadyRegisteredException extends RuntimeException{
    public AccountAlreadyRegisteredException() {
        super("This account is already registered");
    }
}
