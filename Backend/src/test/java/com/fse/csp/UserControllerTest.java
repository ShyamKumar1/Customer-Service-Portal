package com.fse.csp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fse.csp.controller.UserController;
import com.fse.csp.exception.ResourceNotFoundException;
import com.fse.csp.model.User;
import com.fse.csp.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private User user1;
    private User user2;
    private List<User> userList;

    @BeforeEach
    public void setUp() {
        user1 = new User();
        user1.setUserId(1);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");
        user1.setContactNo("1234567890");
        user1.setContactPreference("email");
        user1.setPassword("password");

        user2 = new User();
        user2.setUserId(2);
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setEmail("jane.doe@example.com");
        user2.setContactNo("1234567890");
        user2.setContactPreference("email");
        user2.setPassword("password");

        userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(userList);
        List<User> result = userController.getAllUsers();
        assertEquals(2, result.size());
        assertEquals(userList, result);
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(user1)).thenReturn(user1);
        User result = userController.createUser(user1);
        assertEquals(user1, result);
    }

    @Test
    public void testGetUserByEmail() throws ResourceNotFoundException {
        when(userRepository.findById(user1.getEmail())).thenReturn(Optional.of(user1));
        ResponseEntity<User> result = userController.getUserByEmail(user1.getEmail());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(user1, result.getBody());
    }

    @Test
    public void testUpdateUserDetails() throws ResourceNotFoundException {
        when(userRepository.findById(user1.getEmail())).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn(user1);
        user1.setFirstName("Updated First Name");
        ResponseEntity<User> result = userController.updateServiceRequest(user1.getEmail(), user1);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(user1, result.getBody());
    }
}
