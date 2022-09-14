package com.revature.project1.auth;

import com.revature.project1.users.User;
import com.revature.project1.users.UserDAO;

import javax.servlet.http.HttpServlet;

public class AuthServlet extends HttpServlet {

    private final UserDAO userDAO;

    public AuthServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
