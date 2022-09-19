package com.revature.project1.reimbursements;

import com.revature.project1.common.Request;

import java.sql.Timestamp;

public class UpdateReimbRequest implements Request<Reimbursement> {


    private String reimbId;
    private String statusId;
    private Timestamp resolved;
    private String resolverId;


    public String getReimbId() {
        return reimbId;
    }

    public void setReimbId(String reimbId) {
        this.reimbId = reimbId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getResolverId() {
        return resolverId;
    }

    public void setResolverId(String resolverId) {
        this.resolverId = resolverId;
    }

    @Override
    public String toString() {
        return "UpdateReimbRequet [" +
                "Reimb = '" + reimbId + "' updated]";
    }

    @Override
    public Reimbursement extractEntity() {
        Reimbursement extractedEntity = new Reimbursement();
        extractedEntity.setReimbId(this.reimbId);
        extractedEntity.setStatusId(this.statusId);
        extractedEntity.setResolverId(this.resolverId);
        extractedEntity.setResolved(String.valueOf(this.resolved));

        return extractedEntity;
    }
}