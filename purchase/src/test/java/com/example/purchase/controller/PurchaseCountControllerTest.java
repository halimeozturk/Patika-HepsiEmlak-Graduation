package com.example.purchase.controller;


import com.example.purchase.dto.PurchaseCountDTO;
import com.example.purchase.service.AdvertPackageService;
import com.example.purchase.service.PurchaseCountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PurchaseCountControllerTest {

    @InjectMocks
    private PurchaseCountController purchaseCountController;

    @Mock
    private PurchaseCountService purchaseCountService;

    private PurchaseCountDTO preparePurchaseCountDTO(){
        PurchaseCountDTO purchaseCountDTO = new PurchaseCountDTO();
        purchaseCountDTO.setTotal(10);
        purchaseCountDTO.setRemainingTotal(30);
        purchaseCountDTO.setId(1L);
        purchaseCountDTO.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        purchaseCountDTO.setStartDate(ZonedDateTime.now());
        purchaseCountDTO.setEndDate(ZonedDateTime.now().plusDays(3));
        return purchaseCountDTO;
    }
    @Test
    void getPurchaseCount(){
        ResponseEntity res = purchaseCountController.getPurchaseCount(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertEquals(res.getStatusCodeValue(), 200);
    }


    @Test
    void update(){
        ResponseEntity res = purchaseCountController.update(preparePurchaseCountDTO());
        assertEquals(res.getStatusCodeValue(), 200);
    }



}
