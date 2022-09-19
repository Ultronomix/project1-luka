package com.revature.project1.users;

import com.revature.project1.common.exceptions.InvalidRequestException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDAOTest {

    UserService sut;
    UserDAO mockUserDAO;

    @BeforeEach
    public void setup() {
        mockUserDAO = Mockito.mock(UserDAO.class);
        sut = new UserService(mockUserDAO);
    }

    @AfterEach
    public void cleanUp() {
        Mockito.reset(mockUserDAO);

    }


    @Test
    void GetAllUsers() {
        List<User> mockList = new ArrayList<>();
        User userStub = new User();
        mockList.add(userStub);
        when(mockUserDAO.getAllUsers()).thenReturn(mockList);
        List<UserResponse> expectedResult = mockUserDAO.getAllUsers().stream().map(UserResponse::new).collect(Collectors.toList());

        List<UserResponse> actualResult = sut.getAllUsers();

        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
        verify(mockUserDAO, times(2)).getAllUsers();
    }

    @Test
    void getUserByUserIdUserById() {
        String idStub = null;

        assertThrows(InvalidRequestException.class, () -> {
            sut.getUserById(idStub);
        });

        verify(mockUserDAO, times(0)).findUserById(anyString());
    }

    @Test
    void findUserByUsername() {
    }

    @Test
    void findUserByEmail() {
    }

    @Test
    void findUserByUsernameAndPassword() {
    }

    @Test
    void isUsernameTaken() {
    }

    @Test
    void isEmailTaken() {
    }

    @Test
    void newUsername() {
    }
}