package com.revature.project1.auth;

import com.revature.project1.common.exceptions.AuthenticationException;
import com.revature.project1.users.User;
import com.revature.project1.users.UserDAO;

import java.util.List;

public class AuthService {


    private final UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User authenticate(Credentials credentials) {

        if (credentials == null || credentials.getUsername().length() < 4 || credentials.getPassword().length() < 8) {
            throw new RuntimeException("Invalid request data provided!");
        }
        return userDAO.findUserByUsernameAndPassword(credentials.getUsername(), credentials.getPassword()).orElseThrow(AuthenticationException::new);
    }
}
