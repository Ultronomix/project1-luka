package com.revature.project1.reimbursements;

import com.revature.project1.common.ResourceCreationResponse;
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
    public ResourceCreationResponse newReimb(NewReimbRequest newReimb) {

        if (newReimb == null) {
            throw new InvalidRequestException("Provided request payload was null.");

        }

        if (newReimb.getAmount() == 0) {
            throw new InvalidRequestException("Provided request payload was null.");

        }

        if (newReimb.getDescription() == null) {
            throw new InvalidRequestException("Provided request payload was null.");
        }

        if (newReimb.getAuthorId() == null) {
            throw new InvalidRequestException("Provided request payload was null.");
        }

        if (newReimb.getTypeId() == null) {
            throw new InvalidRequestException("Provided request payload was null.");

        }


        Reimbursement reimbToPersist = newReimb.extractEntity();
        String newReimbId = reimbDAO.newReimbursement(reimbToPersist);
        return new ResourceCreationResponse(newReimbId);
    }
}
