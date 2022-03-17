package com.example.purchase.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userClient", url = "http://localhost:8085/users")
public interface UserClient {

    @GetMapping("/exists/{id}")
    boolean existsUser(@PathVariable Long id);
}
