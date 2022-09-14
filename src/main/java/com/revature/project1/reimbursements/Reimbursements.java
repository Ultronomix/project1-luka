package com.revature.project1.reimbursements;

import java.util.Objects;

public class Reimbursements {
    public String reimbId;
    public Double amount;
    public String description;
    public Byte paymentId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Byte paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursements that = (Reimbursements) o;
        return paymentId == that.paymentId && reimbId.equals(that.reimbId) && amount.equals(that.amount) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbId, amount, description, paymentId);
    }

    @Override
    public String toString() {
        return "Reimbursements{" +
                "reimbId='" + reimbId + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", paymentId=" + paymentId +
                '}';
    }
}
