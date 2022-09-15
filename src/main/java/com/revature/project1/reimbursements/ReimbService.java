package com.revature.project1.reimbursements;

import com.revature.project1.common.exceptions.InvalidRequestException;
import com.revature.project1.common.exceptions.ResourceNotFoundException;
import com.revature.project1.users.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ReimbService {
    private final ReimbDAO reimbDAO;

    public ReimbService(ReimbDAO reimbDAO) {
        this.reimbDAO = reimbDAO;
    }

    public List<ReimbResponse> getAllReimbursements() {
        return reimbDAO.getAllReimbursements()
                .stream()
                .map(ReimbResponse::new)
                .collect(Collectors.toList());
    }

    public ReimbResponse getReimbById(String reimbId) {
        if (reimbId == null || reimbId.length() <= 0) {
            throw new InvalidRequestException("ID cannot be empty!");
        }
        try {
            return reimbDAO.getReimbursementById(reimbId)
                    .map(ReimbResponse::new)
                    .orElseThrow(ResourceNotFoundException::new);

        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("An invalid id string was provided.");
        }
    }

}
