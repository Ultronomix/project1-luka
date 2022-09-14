package com.revature.project1.users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class UserServlet extends HttpServlet {

    private final UserDAO userDAO;

    public UserServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       List<User> allUsers = userDAO.getAllUsers();

        resp.getWriter().write(allUsers.toString());


    }
}
