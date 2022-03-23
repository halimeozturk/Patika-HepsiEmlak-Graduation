package com.example.user.controller;

import com.example.user.dto.AuthRequestDTO;
import com.example.user.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    private AuthRequestDTO prepareAuth(){
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setEmail("ozturkk.halimee@gmail.com");
        authRequestDTO.setPassword("123456");
        return authRequestDTO;
    }

    @Test
    void getToken(){
        ResponseEntity res = authController.getToken(prepareAuth());
        assertEquals(res.getStatusCodeValue(), 200);
    }


}
