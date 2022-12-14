package com.revature.project1.reimbursements;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.common.ErrorResponse;
import com.revature.project1.common.ResourceCreationResponse;
import com.revature.project1.common.exceptions.*;
import com.revature.project1.users.UserResponse;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ReimbServlet extends HttpServlet {
    private final ReimbService reimbService;
    public ReimbServlet(ReimbService reimbService) {
        this.reimbService = reimbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        resp.setContentType("application/json");

        HttpSession userSession = req.getSession(false);
        if (userSession == null) {
            resp.setStatus(401);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(401, "Not authenticated with the system, please log in.")));
            return;
        }
        String idParam = req.getParameter("reimbId");
        UserResponse requester = (UserResponse) userSession.getAttribute("authUser");

        if (!requester.getRoleId().equals("0003") && !requester.getRoleId().equals("0002")) {
            resp.setStatus(403);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(403, "You do not have permission to communicate with this endpoint.")));
            return;
        }

        try {
            if (idParam == null) {
                List<ReimbResponse> allUsers = reimbService.getAllReimbursements();
                resp.getWriter().write(jsonMapper.writeValueAsString(allUsers));
            } else {
                ReimbResponse reimbById = reimbService.getReimbById(idParam);
                resp.getWriter().write(jsonMapper.writeValueAsString(reimbById));
            }
        } catch (InvalidRequestException | JsonMappingException e) {
            resp.setStatus(400);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(400, e.getMessage())));
        } catch (ResourceNotFoundException e) {
            resp.setStatus(404);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(400, e.getMessage())));
        } catch (DataSourceException e) {
            resp.setStatus(500);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(500, e.getMessage())));
        }
    }
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        resp.setContentType("application/json");
        HttpSession reimbSession = req.getSession(false);

        if (reimbSession == null) {
            resp.setStatus(401);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(401, "You do not have permission to communicate with this endpoint.")));
            return;
        }
        UserResponse requester = (UserResponse) reimbSession.getAttribute("authUser");

        if (!requester.getRoleId().equals("0003") && !requester.getRoleId().equals("0002")) {
            resp.setStatus(403);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(403, "You do not have permission to communicate with this endpoint.")));
            return;
        }
        try {

            NewReimbRequest requestBody = jsonMapper.readValue(req.getInputStream(), NewReimbRequest.class);
            requestBody.setAuthorId(requester.getId());
            ResourceCreationResponse responseBody = reimbService.newReimb(requestBody);
            resp.getWriter().write(jsonMapper.writeValueAsString(responseBody));

        } catch (InvalidRequestException | JsonMappingException e) {
            resp.setStatus(400); // BAD REQUEST;
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(400, e.getMessage())));
        } catch (ResourcePersistenceException e) {
            resp.setStatus(409); // CONFLICT; indicates that the provided resource could not be saved without conflicting with other data
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(409, e.getMessage())));
        } catch (DataSourceException e) {
            resp.setStatus(500); // INTERNAL SERVER ERROR; general error indicating a problem with the server
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(500, e.getMessage())));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        resp.setContentType("application/json");
        HttpSession reimbSession = req.getSession(false);

        if (reimbSession == null) {
            resp.setStatus(401);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(401, "Not authenticated with the system, please log in.")));
            return;
        }

        UserResponse requester = (UserResponse) reimbSession.getAttribute("authUser");

        if (!requester.getRoleId().equals("0002")) {
            resp.setStatus(403);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(403, "You do not have permission to communicate with this endpoint.")));
            return;
        }
        try {

            UpdateReimbRequest request = jsonMapper.readValue(req.getInputStream(), UpdateReimbRequest.class);
            reimbService.updateStatus(request);

        } catch (InvalidRequestException | JsonMappingException e){
            resp.setStatus(400);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(400, e.getMessage())));
        } catch (AuthenticationException e){
            resp.setStatus(400);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(400, e.getMessage())));
        } catch (DataSourceException e){
            resp.setStatus(500);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(500, e.getMessage())));
        }
    }
}





