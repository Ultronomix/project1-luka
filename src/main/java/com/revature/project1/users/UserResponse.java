package com.revature.project1.users;

import java.io.Serializable;
import java.util.Objects;

// Example of a response DTO
public class UserResponse implements Serializable {

    private String userId;
    private String givenName;
    private String surname;
    private String email;
    private String username;
    private String roleId;

    public UserResponse(User subject) {
        this.userId = subject.getUserId();
        this.givenName = subject.getGivenName();
        this.surname = subject.getSurname();
        this.email = subject.getEmail();
        this.username = subject.getUsername();
        this.roleId = subject.getRole();
    }

    public String getId() {
        return userId;
    }

    public void setId(String userId) {
        this.userId = userId;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRole(String role) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(userId, that.userId) && Objects.equals(givenName, that.givenName) && Objects.equals(surname, that.surname) && Objects.equals(email, that.email) && Objects.equals(username, that.username) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, givenName, surname, email, username, roleId);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId='" + userId + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }

}
