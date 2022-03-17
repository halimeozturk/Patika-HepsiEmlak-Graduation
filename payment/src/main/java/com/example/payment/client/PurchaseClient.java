package com.example.payment.client;

import com.example.payment.dto.PurchaseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "purchaseClient", url = "http://localhost:9092/purchases")
public interface PurchaseClient {

    @PostMapping
    ResponseEntity<?> create(@RequestBody PurchaseDTO purchaseDTO);

}
