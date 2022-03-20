package com.example.purchase.controller;

import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping
    ResponseEntity<List<PurchaseDTO>> getAllList(){
        return ResponseEntity.ok(purchaseService.getAllList());
    }

    @PostMapping
    ResponseEntity<PurchaseDTO> create(@RequestBody PurchaseDTO purchaseDTO, @RequestHeader(value = "id") UUID id){
        return ResponseEntity.ok(purchaseService.create(purchaseDTO,id));
    }

    @PutMapping
    ResponseEntity<PurchaseDTO> update(@RequestBody PurchaseDTO purchaseDTO, @RequestHeader(value = "id") UUID id){
        return ResponseEntity.ok(purchaseService.update(purchaseDTO,id));
    }


    @GetMapping("/{id}")
    ResponseEntity<PurchaseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(purchaseService.getById(id));
    }
}
