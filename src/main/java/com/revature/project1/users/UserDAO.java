package com.revature.project1.users;

import com.revature.project1.common.exceptions.DataSourceException;
import com.revature.project1.common.datasource.ConnectionFactory;
import java.io.File;
import java.io.FileWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDAO {

    private final String baseSelect = "SELECT au.user_id, au.username, au.email, au.password, au.given_name, au.surname, au.is_active, au.role_id " +
            "FROM users au "+
            "JOIN user_roles ur "+
            "ON au.role_id = ur.role_id ";

    public List<User> getAllUsers() {

        List<User> allUsers = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(baseSelect);

            allUsers = mapResultSet(rs);

        } catch (SQLException e) {
            System.err.println("Something went wrong when communicating with the database");
            e.getMessage();
        }
        log("INFO", "Successfully found all users: ");
        return allUsers;
    }

    public Optional<User> findUserById (String userId){
        String sql = baseSelect + "WHERE au.user_id = ?";

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, userId);

            ResultSet rs = pstmt.executeQuery();

            log("INFO", "Successfully persisted new user with id: " + userId);
            return mapResultSet(rs).stream().findFirst();
        } catch (SQLException e){
            throw new DataSourceException(e);
        }
    }

    public Optional<User> findUserByUsername (String username){
        String sql = baseSelect + "WHERE au.username = ?";

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            log("INFO", "Successfully found user with username: " + username);
            return mapResultSet(rs).stream().findFirst();
        }catch (SQLException e){
            throw new DataSourceException(e);
        }
    }
    public Optional<User> findUserByEmail(String email){
        String sql = baseSelect + "WHERE au.email = ?";

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            log("INFO", "Successfully found user by email: " + email);
            return mapResultSet(rs).stream().findFirst();
        }catch (SQLException e){
            throw new DataSourceException(e);
        }
    }

    public Optional<User> findUserByUsernameAndPassword(String username,String password){
        String sql = baseSelect + "WHERE au.username = ? AND au.password = ?";

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();
            log("INFO", "Successfully found user with username and password. " );
            return mapResultSet(rs).stream().findFirst();
        }catch (SQLException e){
            throw new DataSourceException(e);
        }
    }
    public boolean isUsernameTaken(String username){
        return findUserByUsername(username).isPresent();
    }
    public boolean isEmailTaken(String email){
        return findUserByEmail(email).isPresent();
    }

    public  String newUsername(User user){
        String sql = "INSERT INTO users (user_id, username, email, password, given_name, surname, is_active, role_id) " +
                "VALUES(?,?,?,?,?,?,true,'0001')";

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getGivenName());
            pstmt.setString(6, user.getSurname());

            pstmt.executeUpdate();

        } catch (SQLException e){
            throw new DataSourceException(e);
        }
        log("INFO", "Successfully persisted new user with id: " + user.getUserId());
        return user.getUserId();
    }

    private List<User> mapResultSet(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getString("user_id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setPassword("password");
            user.setGivenName(rs.getString("given_name"));
            user.setSurname(rs.getString("surname"));
            user.setActive(rs.getBoolean("is_active"));
            user.setRole(rs.getString("role_id"));
            users.add(user);
        }
        return users;
    }

    public void log(String level, String message) {
        try {
            File logFile = new File("logs/app.log");
            logFile.createNewFile();
            BufferedWriter logWriter = new BufferedWriter(new FileWriter(logFile));
            logWriter.write(String.format("[%s] at %s logged: [%s] %s\n", Thread.currentThread().getName(), LocalDate.now(), level.toUpperCase(), message));
            logWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
