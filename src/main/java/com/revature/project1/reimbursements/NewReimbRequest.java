package com.revature.project1.reimbursements;

import java.util.UUID;
import com.revature.project1.common.Request;

public class NewReimbRequest implements Request<Reimbursement> {
    private String reimbId;
    private Double amount;
    private String submitted;
    private String resolved;
    private String description;
    private String authorId;
    private String resolverId;
    private String statusId;
    private String typeId;

    public String getReimbId() {
        return reimbId;
    }

    public void setReimbId(String reimbId) {
        this.reimbId = reimbId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getResolverId() {
        return resolverId;
    }

    public void setResolverId(String resolverId) {
        this.resolverId = resolverId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "NewReimbRequest{" +
                "reimbId='" + reimbId + '\'' +
                ", amount=" + amount +
                ", submitted='" + submitted + '\'' +
                ", resolved='" + resolved + '\'' +
                ", description='" + description + '\'' +
                ", authorId='" + authorId + '\'' +
                ", resolverId='" + resolverId + '\'' +
                ", statusId='" + statusId + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }
    @Override
    public Reimbursement extractEntity() {
        Reimbursement extractEntity = new Reimbursement();
        extractEntity.setReimbId(UUID.randomUUID().toString());
        extractEntity.setAmount(this.amount);
        extractEntity.setSubmitted(this.submitted);
        extractEntity.setResolved(this.resolved);
        extractEntity.setDescription(this.description);
        extractEntity.setAuthorId(this.authorId);
        extractEntity.setResolverId(this.resolverId);
        extractEntity.setStatusId(this.statusId);
        extractEntity.setTypeId(this.typeId);
        return extractEntity;
    }
}
