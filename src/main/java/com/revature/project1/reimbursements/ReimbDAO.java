package com.revature.project1.reimbursements;



import com.revature.project1.common.datasource.ConnectionFactory;
import com.revature.project1.common.exceptions.DataSourceException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReimbDAO {

    private final String baseSelect = "SELECT re.reimb_id, re.amount, re.submitted, re.resolved, re.description, re.author_id, re.resolver_id, re.status_id, re.type_id " +
            "FROM reimbursements re ";

    public List<Reimbursement> getAllReimbursements() {

        List<Reimbursement> allReimbursementsList = new ArrayList<>();

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

    public Optional<Reimbursement> getReimbursementById(String reimb_id) {
        String sql = baseSelect + "WHERE re.reimb_id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, reimb_id);
            ResultSet rs = pstmt.executeQuery();
            return mapResultSet(rs).stream().findFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataSourceException(e);
        }
    }

    private List<Reimbursement> mapResultSet(ResultSet rs) throws SQLException {
        List<Reimbursement> reimbursementsList = new ArrayList<>();
        while (rs.next()) {
            Reimbursement reimbursements = new Reimbursement();
            reimbursements.setReimbId(rs.getString("reimb_id"));
            reimbursements.setAmount(rs.getDouble("amount"));
            reimbursements.setSubmitted(rs.getString("submitted"));
            reimbursements.setResolved(rs.getString("resolved"));
            reimbursements.setDescription(rs.getString("description"));
            reimbursements.setAuthorId(rs.getString("author_id"));
            reimbursements.setResolverId(rs.getString("resolver_id"));
            reimbursements.setStatusId(rs.getString("status_id"));
            reimbursements.setTypeId(rs.getString("type_id"));


            reimbursementsList.add(reimbursements);
        }
        return reimbursementsList;
    }
}
