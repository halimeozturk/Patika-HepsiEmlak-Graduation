package com.example.purchase.controller;

import com.example.purchase.dto.AdvertPackageDTO;
import com.example.purchase.service.AdvertPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advert-packages")
@RequiredArgsConstructor
public class AdvertPackageController {

    private final AdvertPackageService advertPackageService;

    @GetMapping("/{id}")
    ResponseEntity<AdvertPackageDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(advertPackageService.getById(id));
    }
}
