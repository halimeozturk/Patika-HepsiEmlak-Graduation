package com.example.advert.client;

import com.example.advert.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.cloud.openfeign.FeignClient;

import java.util.UUID;

@FeignClient(name = "userClient", url = "http://localhost:8085/users")
public interface UserClient {
    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable UUID id);
}
