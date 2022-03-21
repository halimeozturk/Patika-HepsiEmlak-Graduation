package com.example.advert.controller;

import com.example.advert.dto.AdvertDTO;
import com.example.advert.enums.BuildType;
import com.example.advert.enums.Currency;
import com.example.advert.enums.PublicationType;
import com.example.advert.service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
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

    @GetMapping("/filter")
    ResponseEntity<?> getAllListWithFilter(Pageable pageable,
                                           @RequestParam(value = "creationDate",required = false) @DateTimeFormat(iso =  DateTimeFormat.ISO.DATE_TIME) ZonedDateTime creationDate,
                                           @RequestParam(value = "advertNo",required = false) Long advertNo,
                                           @RequestParam(value = "currency",required = false) Currency currency,
                                           @RequestParam(value = "price",required = false) Double price,
                                           @RequestParam(value = "netSquareMeters",required = false) Double netSquareMeters,
                                           @RequestParam(value = "squareMeters",required = false) Double squareMeters,
                                           @RequestParam(value = "room",required = false) Integer room,
                                           @RequestParam(value = "livingRoom",required = false) Integer livingRoom,
                                           @RequestParam(value = "age",required = false) Integer age,
                                           @RequestParam(value = "bathRoom",required = false) Integer bathRoom,
                                           @RequestParam(value = "numberOfFloor",required = false) Integer numberOfFloor,
                                           @RequestParam(value = "floor",required = false) String floor,
                                           @RequestParam(value = "publicationType",required = false) PublicationType publicationType,
                                           @RequestParam(value = "buildType",required = false) BuildType buildType,
                                           @RequestParam(value = "active",required = false) Boolean active,
                                           @RequestParam(value = "roomAndLivingRoom",required = false) String roomAndLivingRoom,
                                           @RequestParam(value = "province",required = false) String province,
                                           @RequestParam(value = "district",required = false) String district) {
        return ResponseEntity.ok(advertService.getAllListWithFilter(pageable,creationDate,advertNo,currency,price,netSquareMeters,squareMeters,room,livingRoom,age,
                bathRoom,numberOfFloor,floor,publicationType,
                buildType,active,roomAndLivingRoom,province,district));
    }

}
