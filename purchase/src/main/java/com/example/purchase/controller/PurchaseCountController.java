package com.example.purchase.controller;

import com.example.purchase.dto.PurchaseCountDTO;
import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.model.PurchaseCount;
import com.example.purchase.service.PurchaseCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/purchase-counts")
@RequiredArgsConstructor
public class PurchaseCountController {
    private final PurchaseCountService purchaseCountService;

    @GetMapping("/{id}")
    ResponseEntity<PurchaseCountDTO> getPurchaseCount(@PathVariable UUID id){
        return ResponseEntity.ok(purchaseCountService.getPurchaseCount(id));
    }

    @PutMapping
    ResponseEntity<PurchaseCountDTO> update(@RequestBody PurchaseCountDTO purchaseCountDTO){
        return ResponseEntity.ok(purchaseCountService.update(purchaseCountDTO));
    }
}
