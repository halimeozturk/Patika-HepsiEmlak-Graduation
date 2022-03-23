package com.example.user.controller;

import com.example.user.dto.UserDTO;
import com.example.user.model.User;
import com.example.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserDTO prepareMockUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        userDTO.setFirstName("Halime");
        userDTO.setLastName("Öztürk");
        userDTO.setEmail("ozturkk.halimee@gmail.com");
        userDTO.setCreationDate(ZonedDateTime.now());
        userDTO.setModificationDate(ZonedDateTime.now());
        return userDTO;
    }
    @Test
    void getAllList(){
        ResponseEntity res = userController.getAllList();
        assertEquals(res.getStatusCodeValue(), 200);
    }


    @Test
    void create(){
        ResponseEntity res = userController.create(prepareMockUserDTO());
        assertEquals(res.getStatusCodeValue(), 200);
    }

    @Test
    void getUserById(){
        ResponseEntity res = userController.getUserById(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertEquals(res.getStatusCodeValue(), 200);
    }

    @Test
    void getUserByEmail(){
        ResponseEntity res = userController.getUserByEmail("ozturkk.halimee@gmail.com");
        assertEquals(res.getStatusCodeValue(), 200);
    }

    @Test
    void existsUser(){
        boolean res = userController.existsUser(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertEquals(res, false);
    }
}
