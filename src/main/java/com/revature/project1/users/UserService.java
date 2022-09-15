package com.revature.project1.users;

import com.revature.project1.common.ResourceCreationResponse;
import com.revature.project1.common.exceptions.InvalidRequestException;
import com.revature.project1.common.exceptions.ResourceNotFoundException;
import com.revature.project1.common.exceptions.ResourcePersistenceException;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<UserResponse> getAllUsers() {
        return userDAO.getAllUsers()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(String id) {

        if (id == null || id.length() <= 0) {
            throw new InvalidRequestException("ID cannot be empty!");
        }

        try {


            return userDAO.findUserById(id)
                    .map(UserResponse::new)
                    .orElseThrow(ResourceNotFoundException::new);

        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("An invalid id string was provided.");
        }



    }

    public ResourceCreationResponse register(NewUserRequest newUser) {

        if (newUser == null) {
            throw new InvalidRequestException("Provided request payload was null.");
        }

        if (newUser.getGivenName() == null || newUser.getGivenName().length() <= 0 ||
                newUser.getSurname() == null || newUser.getSurname().length() <= 0)
        {
            throw new InvalidRequestException("A non-empty given name and surname must be provided");
        }

        if (newUser.getEmail() == null || newUser.getEmail().length() <= 0) {
            throw new InvalidRequestException("A non-empty email must be provided.");
        }

        if (newUser.getUsername() == null || newUser.getUsername().length() < 4) {
            throw new InvalidRequestException("A username with at least 4 characters must be provided.");
        }

        if (newUser.getPassword() == null || newUser.getPassword().length() < 8) {
            throw new InvalidRequestException("A password with at least 8 characters must be provided.");
        }

        if (userDAO.isEmailTaken(newUser.getEmail())) {
            throw new ResourcePersistenceException("Resource not persisted! The provided email is already taken.");
        }

        if (userDAO.isUsernameTaken(newUser.getUsername())) {
            throw new ResourcePersistenceException("Resource not persisted! The provided username is already taken.");
        }

        User userToPersist = newUser.extractEntity();
        String newUserId = userDAO.newUsername(userToPersist);
        return new ResourceCreationResponse(newUserId);

    }
}
