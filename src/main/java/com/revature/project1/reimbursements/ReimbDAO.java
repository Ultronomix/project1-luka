package com.revature.project1.reimbursements;

import com.revature.project1.common.datasource.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReimbDAO {

    private final String baseSelect = "SELECT * " +
            "FROM reimbursements ";

    public List<Reimbursements> getAllReimbursements() {

        List<Reimbursements> allReimbursementsList = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            // JDBC Statement objects are vulnerable to SQL injection
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(baseSelect);

            allReimbursementsList = mapResultSet(rs);

        } catch (SQLException e) {
            System.err.println("Something went wrong when communicating with the database");
            e.printStackTrace();
        }
        return allReimbursementsList;

    }

    private List<Reimbursements> mapResultSet(ResultSet rs) throws SQLException {
        List<Reimbursements> reimbursementsList = new ArrayList<>();
        while (rs.next()) {
            Reimbursements reimbursements = new Reimbursements();
            reimbursements.setReimbId(rs.getString("reimb_id"));
            reimbursements.setAmount(rs.getDouble("amount"));
            reimbursements.setDescription(rs.getString("description"));
            reimbursements.setPaymentId(rs.getByte("payment_id"));

            reimbursementsList.add(reimbursements);
        }
        return reimbursementsList;
    }
}
