package com.example.payment.client;

import com.example.payment.dto.PurchaseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userClient", url = "http://localhost:8085/users")
public interface UserClient {

    @GetMapping("/{email}")
    ResponseEntity<?> getUserById(@PathVariable String email);

}
