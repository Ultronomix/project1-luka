package com.revature.project1.reimbursements;

import java.io.Serializable;
import java.util.Objects;

public class ReimbResponse implements Serializable {
    private String reimbId;
    private Double amount;
    private String submitted;
    private String resolved;
    private String description;
    private String authorId;
    private String resolverId;
    private String statusId;
    private String typeId;

    public ReimbResponse(Reimbursement reimb) {
        this.reimbId = reimb.getReimbId();
        this.amount = reimb.getAmount();
        this.submitted = reimb.getSubmitted();
        this.resolved = reimb.getResolved();
        this.description = reimb.getDescription();
        this.authorId = reimb.getAuthorId();
        this.resolverId = reimb.getResolverId();
        this.statusId = reimb.getStatusId();
        this.typeId = reimb.getTypeId();
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReimbResponse that = (ReimbResponse) o;
        return reimbId.equals(that.reimbId) && amount.equals(that.amount) && submitted.equals(that.submitted) && resolved.equals(that.resolved) && description.equals(that.description) && authorId.equals(that.authorId) && resolverId.equals(that.resolverId) && statusId.equals(that.statusId) && typeId.equals(that.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbId, amount, submitted, resolved, description, authorId, resolverId, statusId, typeId);
    }

    @Override
    public String toString() {
        return "ReimbResponse{" +
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
}
