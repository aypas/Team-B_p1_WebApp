package com.revature.p1.exceptions;

public class EmailUnavailableException extends ResourcePersistenceException {
    public EmailUnavailableException() {
        super("The provided email is already taken!");
    }
}
