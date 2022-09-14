package com.revature.project1.users;

import com.revature.project1.common.exceptions.DataSourceException;
import com.revature.project1.users.datasource.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDAO {
    private final String baseSelect = "SELECT au.id, au.given_name, au.surname, au.email, au.username, au.role_id, ur.role " +
            "FROM app_users au " +
            "JOIN user_roles ur " +
            "ON au.role_id = ur.id ";

    public List<User> getAllUsers() {

        List<User> allUsers = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            // JDBC Statement objects are vulnerable to SQL injection
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(baseSelect);

            allUsers = mapResultSet(rs);

        } catch (SQLException e) {
            System.err.println("Something went wrong when communicating with the database");
            e.printStackTrace();
        }
        return allUsers;
    }

    private List<User> mapResultSet(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getString("user_id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setGivenName(rs.getString("given_name"));
            user.setSurname(rs.getString("surname"));
            user.setActive(rs.getBoolean("is_active"));
            users.add(user);
        }
        return users;
    }

    public Optional<User> findUserByUsernameAndPassword(String username, String password) {

        String sql = baseSelect + "WHERE au.username = ? AND au.password = ?";

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            // JDBC Statement objects are vulnerable to SQL injection
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return mapResultSet(rs).stream().findFirst();

        } catch (SQLException e) {
            // TODO log this exception
            throw new DataSourceException(e);
        }

    }


}
