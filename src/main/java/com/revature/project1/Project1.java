package com.revature.project1;

import com.revature.project1.users.UserDAO;

public class Project1 {
    public static void main(String[] args) {
        System.out.println("Initial commit!");


        UserDAO userDao = new UserDAO();
        System.out.println(userDao.getAllUsers());
    }
}
