package com.example.user.controller;

import com.example.user.dto.AuthRequestDTO;
import com.example.user.dto.AuthResponseDTO;
import com.example.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping
    public ResponseEntity<AuthResponseDTO> getToken(@RequestBody AuthRequestDTO request){
        return new ResponseEntity<>(authService.getToken(request), HttpStatus.OK);
    }
}
