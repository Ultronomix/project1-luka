package com.revature.project1.reimbursements;

import java.util.Objects;

public class Reimbursement {
    private String reimbId;
    private Double amount;
    private String submitted;
    private String resolved;
    private String description;
    private String authorId;
    private String resolverId;
    private String statusId;
    private String typeId;

    public Reimbursement() {
        super();
    }
    public Reimbursement(String reimbId, Double amount, String submitted, String resolved, String description, String authorId, String resolverId, String statusId, String typeId) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;
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
        Reimbursement that = (Reimbursement) o;
        return reimbId.equals(that.reimbId) && amount.equals(that.amount) && submitted.equals(that.submitted) && resolved.equals(that.resolved) && description.equals(that.description) && authorId.equals(that.authorId) && resolverId.equals(that.resolverId) && statusId.equals(that.statusId) && typeId.equals(that.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbId, amount, submitted, resolved, description, authorId, resolverId, statusId, typeId);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId='" + reimbId + '\'' +
                ", amount='" + amount + '\'' +
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
