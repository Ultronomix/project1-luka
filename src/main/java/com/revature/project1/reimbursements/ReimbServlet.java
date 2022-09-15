package com.revature.project1.reimbursements;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.common.ErrorResponse;
import com.revature.project1.common.exceptions.DataSourceException;
import com.revature.project1.common.exceptions.InvalidRequestException;
import com.revature.project1.common.exceptions.ResourceNotFoundException;
import com.revature.project1.users.UserDAO;
import com.revature.project1.reimbursements.ReimbDAO;
import com.revature.project1.users.UserResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ReimbServlet extends HttpServlet {

    private final ReimbDAO reimbDAO;

    public ReimbServlet(ReimbDAO reimbDAO) {
        this.reimbDAO = reimbDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        resp.setContentType("application/json");

        HttpSession userSession = req.getSession(false);
        if (userSession == null) {
            resp.setStatus(401);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(401, "Requester not authenticated, please log in")));
            return;
        }

        String idParam = req.getParameter("user_id");

        UserResponse requester = (UserResponse) userSession.getAttribute("authUser");
        if (!requester.getRoleId().equals("0001") && !requester.getRoleId().equals("0002")) {
            resp.setStatus(403);
            resp.getWriter().write(jsonMapper.writeValueAsString(new ErrorResponse(403, "Requester is not permitted to communicate with this endpoint")));
            return;
        }

        try {
            if (idParam == null) {
                List<Reimbursement> allUsers = reimbDAO.getAllReimbursements();
                resp.getWriter().write(jsonMapper.writeValueAsString(allUsers));
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
}




