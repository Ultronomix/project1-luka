package com.revature.project1.common.exceptions;

import com.revature.project1.auth.AuthService;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException() {
        super("Could not find a user with the provided credentials ");

        }
}
