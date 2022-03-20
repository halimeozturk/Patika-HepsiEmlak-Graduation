package com.example.advert.controller;

import com.example.advert.dto.AdvertDTO;
import com.example.advert.service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/adverts")
@RequiredArgsConstructor
public class AdvertController {
    private final AdvertService advertService;

    @GetMapping
    ResponseEntity<List<AdvertDTO>> getAllList(){
        return ResponseEntity.ok(advertService.getAllList());
    }

    @PostMapping
    ResponseEntity<AdvertDTO> create(@RequestBody AdvertDTO advertDTO,@RequestHeader(value = "id") UUID id){
        return ResponseEntity.ok(advertService.create(advertDTO,id));
    }

    @PutMapping
    ResponseEntity<AdvertDTO> update(@RequestBody AdvertDTO advertDTO){
        return ResponseEntity.ok(advertService.update(advertDTO));
    }

    @GetMapping("/advertNo/{advertNo}")
    ResponseEntity<AdvertDTO> getAdvertByAdvertNo(@PathVariable Long advertNo){
        return ResponseEntity.ok(advertService.getAdvertByAdvertNo(advertNo));
    }

    @GetMapping("/{id}")
    ResponseEntity<AdvertDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(advertService.getById(id));
    }
}
