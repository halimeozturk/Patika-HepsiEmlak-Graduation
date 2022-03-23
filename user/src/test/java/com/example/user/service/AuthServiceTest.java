package com.example.user.service;

import com.example.user.dto.AuthRequestDTO;
import com.example.user.dto.AuthResponseDTO;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import com.example.user.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository authRepository;

    @Mock
    private  JwtUtil jwtUtil;

    void prepareAuthResponseDTO(){
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken("tok-bla-bla-bla");
    }

    private AuthRequestDTO prepareAuth(){
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setEmail("ozturkk.halimee@gmail.com");
        authRequestDTO.setPassword("123456");
        return authRequestDTO;
    }
    private User prepareMockUser() {
        User user = new User();
        user.setId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        user.setFirstName("Halime");
        user.setLastName("Öztürk");
        user.setPassword("123456");
        user.setEmail("ozturkk.halimee@gmail.com");
        user.setCreationDate(ZonedDateTime.now());
        user.setModificationDate(ZonedDateTime.now());
        return user;
    }

    @Test
    void getToken(){
        Mockito.when(authRepository.findByEmail(any())).thenReturn(java.util.Optional.of(prepareMockUser()));
        Mockito.when(jwtUtil.generateToken(prepareMockUser())).thenReturn(new String());
        AuthResponseDTO authResponseDTO = authService.getToken(prepareAuth());
        assertNotNull(authResponseDTO);
    }

}
