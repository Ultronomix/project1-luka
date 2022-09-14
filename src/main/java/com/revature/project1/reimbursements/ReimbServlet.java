package com.revature.project1.reimbursements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.users.UserDAO;
import com.revature.project1.reimbursements.ReimbDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReimbServlet extends HttpServlet {

    private final ReimbDAO reimbDAO;

    public ReimbServlet(ReimbDAO reimbDAO) {
        this.reimbDAO = reimbDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        resp.getWriter().write("DoGet works on Reimb Servlet");


    }
}
