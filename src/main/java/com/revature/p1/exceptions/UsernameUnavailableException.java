package com.revature.p1.exceptions;

public class UsernameUnavailableException extends ResourcePersistenceException {
    public UsernameUnavailableException() {
        super("The provided username is already taken!");
    }
}
