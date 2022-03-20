package com.example.payment.client;

import com.example.payment.dto.PurchaseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(name = "purchaseClient", url = "http://localhost:8088/purchases")
public interface PurchaseClient {

    @PostMapping
    ResponseEntity<PurchaseDTO> create(@RequestBody PurchaseDTO purchaseDTO, @RequestHeader(value = "id") UUID id);
}
