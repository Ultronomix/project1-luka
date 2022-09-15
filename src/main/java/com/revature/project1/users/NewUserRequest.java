package com.revature.project1.users;

import com.revature.project1.common.Request;

import java.util.UUID;

public class NewUserRequest implements Request<User> {

    private String givenName;
    private String surname;
    private String email;
    private String username;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public User extractEntity() {
        User extractedEntity = new User();
        extractedEntity.setUserId(UUID.randomUUID().toString());
        extractedEntity.setGivenName(this.givenName);
        extractedEntity.setSurname(this.surname);
        extractedEntity.setEmail(this.email);
        extractedEntity.setUsername(this.username);
        extractedEntity.setPassword(this.password);
        return extractedEntity;
    }

}
