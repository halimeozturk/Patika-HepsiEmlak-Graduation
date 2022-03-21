package com.example.email.client;

import com.example.email.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "userClient", url = "http://localhost:8085/users")
public interface UserClient {

    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable UUID id);
}
