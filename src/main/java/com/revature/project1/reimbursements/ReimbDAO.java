package com.revature.project1.reimbursements;



import com.revature.project1.common.datasource.ConnectionFactory;
import com.revature.project1.common.exceptions.DataSourceException;
import com.revature.project1.users.User;
import java.sql.Timestamp;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReimbDAO {

    private final String baseSelect = "SELECT reimbursements.reimb_id, reimbursements.amount, reimbursements.submitted, reimbursements.resolved, reimbursements.description, reimbursements.author_id, reimbursements.resolver_id, reimbursements.status_id, reimbursements.type_id " +
            "FROM reimbursements ";

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

    public Optional<Reimbursement> getReimbursementById(String reimbId) {
        String sql = baseSelect + "WHERE reimbursements.reimb_id = ?";
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, reimbId);
            ResultSet rs = pstmt.executeQuery();
            return mapResultSet(rs).stream().findFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataSourceException(e);
        }
    }

    public  String newReimbursement(Reimbursement reimbursement){
        String sql = "INSERT INTO reimbursements (reimb_id, amount, submitted, resolved, description, author_id, resolver_id, status_id, type_id) " +
                "VALUES(?,?,?,null,?,?,?,?,?)";
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setDouble(2, reimbursement.getAmount());
            pstmt.setTimestamp(3, timestamp);
            pstmt.setString(4, reimbursement.getDescription());
            pstmt.setString(5, reimbursement.getAuthorId());
            pstmt.setString(6, reimbursement.getResolverId());
            pstmt.setString(7, reimbursement.getStatusId());
            pstmt.setString(8, reimbursement.getTypeId());
            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
            throw new DataSourceException(e);
        }
        return reimbursement.getReimbId();

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
