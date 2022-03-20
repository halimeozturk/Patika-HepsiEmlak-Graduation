package com.example.advert.client;

import com.example.advert.dto.PurchaseCountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "purchaseCountClient", url = "http://localhost:8088/purchase-counts")
public interface PurchaseCountClient {

    @GetMapping("/{id}")
    ResponseEntity<PurchaseCountDTO> getPurchaseCount(@PathVariable UUID id);

    @PutMapping
    ResponseEntity<PurchaseCountDTO> update(@RequestBody PurchaseCountDTO purchaseCountDTO);
}
