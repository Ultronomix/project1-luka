package com.revature.project1.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.common.ErrorResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class UserServlet extends HttpServlet {

    private final UserDAO userDAO;

    public UserServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        resp.setContentType("application/json");

        HttpSession userSession = req.getSession(false);

        if(userSession == null) {
            resp.setStatus(401);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(401, "Requester is not authenticated with the system, please log in.")));
            return;
        }

        List<User> allUsers = userDAO.getAllUsers();
        resp.getWriter().write(jsonMapper.writeValueAsString(allUsers));


    }
}
