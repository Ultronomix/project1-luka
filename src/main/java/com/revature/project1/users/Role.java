package com.revature.project1.users;

import java.util.Objects;

public class Role {

    private String userId;
    private String name;

    public Role(String userId,String role){
        this.userId=userId;
        this.name = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return userId.equals(role.userId) && name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }


    @Override
    public String toString() {
        return "Role{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
