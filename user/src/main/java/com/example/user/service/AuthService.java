package com.example.user.service;

import com.example.user.dto.AuthRequestDTO;
import com.example.user.dto.AuthResponseDTO;
import com.example.user.exception.GenericServiceException;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import com.example.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository authRepository;

    private final JwtUtil jwtUtil;

    public AuthResponseDTO getToken(AuthRequestDTO request){
        User user = authRepository.findByEmail(request.getEmail()).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"User Email not found"));

        if (!isValidPassword(user.getPassword(), request.getPassword())) {
            log.error("User's password not valid " + request.getEmail());
            throw new GenericServiceException(GenericServiceException.INVALID_REQUEST,"User's password not valid");
        }

        return new AuthResponseDTO(jwtUtil.generateToken(user));
    }

    public static boolean isValidPassword(String password, String password2) {
        return password.equals(password2);
    }
}
