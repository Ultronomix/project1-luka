package com.revature.project1.users;

import java.util.Objects;

public class User {

    private int id;
    private String username;
    private int paycheck;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPaycheck() {
        return paycheck;
    }

    public void setPaycheck(int paycheck) {
        this.paycheck = paycheck;
    }

    public User() {
        super();
    }

    public User(int id, String username, int paycheck) {
        this.id = id;
        this.username = username;
        this.paycheck = paycheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && paycheck == user.paycheck && username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, paycheck);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", paycheck=" + paycheck +
                '}';
    }
}
