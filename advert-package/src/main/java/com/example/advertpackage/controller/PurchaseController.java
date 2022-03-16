package com.example.advertpackage.controller;

import com.example.advertpackage.dto.PurchaseDTO;
import com.example.advertpackage.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @GetMapping
    ResponseEntity<List<PurchaseDTO>> getAllList(){
        return ResponseEntity.ok(purchaseService.getAllList());
    }

    @PostMapping
    ResponseEntity<PurchaseDTO> create(@RequestBody PurchaseDTO purchaseDTO){
        return ResponseEntity.ok(purchaseService.create(purchaseDTO));
    }

    @PutMapping
    ResponseEntity<PurchaseDTO> update(@RequestBody PurchaseDTO purchaseDTO){
        return ResponseEntity.ok(purchaseService.update(purchaseDTO));
    }


    @GetMapping("/{id}")
    ResponseEntity<PurchaseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(purchaseService.getById(id));
    }
}
