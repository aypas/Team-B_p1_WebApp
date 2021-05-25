package com.revature.p1.exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Could not authenticate using the provided credentials");
    }
}
