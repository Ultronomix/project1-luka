package com.revature.project1.auth;

import com.revature.project1.common.ErrorResponse;
import com.revature.project1.common.exceptions.AuthenticationException;
import com.revature.project1.common.exceptions.DataSourceException;
import com.revature.project1.common.exceptions.InvalidRequestException;
import com.revature.project1.users.UserResponse;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthServlet extends HttpServlet {
    private final AuthService authService;
    public AuthServlet(AuthService authService) {
        this.authService = authService;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        resp.setContentType("application/json");

        try {
            resp.setStatus(200);
            Credentials credentials = jsonMapper.readValue(req.getInputStream(), Credentials.class);
            UserResponse responseBody = authService.authenticate(credentials);
            HttpSession userSession = req.getSession();
            userSession.setAttribute("authUser", responseBody);
            resp.getWriter().write(jsonMapper.writeValueAsString(responseBody));

        } catch (InvalidRequestException | JsonMappingException e) {
            resp.setStatus(400); // BAD REQUEST
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(400, e.getMessage())));
        } catch (AuthenticationException e) {
            resp.setStatus(401); // UNAUTHORIZED; typically sent back when login fails or if a protected endpoint is hit by an unauthenticated user
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(401, e.getMessage())));
        } catch (DataSourceException e) {
            resp.setStatus(500); // INTERNAL SERVER ERROR; general error indicating a problem with the server
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(500, e.getMessage())));
        }

    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate(); // this effectively "logs out" the requester by invalidating the session within the server
    }
}
