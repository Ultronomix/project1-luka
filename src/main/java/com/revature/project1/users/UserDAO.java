package com.revature.project1.users;

import com.revature.project1.common.datasource.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final String select = "select * from app_users";

    public List<User> getAllUsers() {

        List<User> allUsers = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            // JDBC Statement objects are vulnerable to SQL injection
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(select);

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
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPaycheck(rs.getInt("paycheck"));
            users.add(user);
        }
        return users;
    }


}
