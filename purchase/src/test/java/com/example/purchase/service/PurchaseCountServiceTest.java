package com.example.purchase.service;


import com.example.purchase.dto.PurchaseCountDTO;
import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.mapper.PurchaseCountMapper;
import com.example.purchase.model.Purchase;
import com.example.purchase.model.PurchaseCount;
import com.example.purchase.repository.PurchaseCountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class PurchaseCountServiceTest {

    @InjectMocks
    private PurchaseCountService purchaseCountService;

    @Mock
    private PurchaseCountRepository purchaseCountRepository;
    @Mock
    private PurchaseCountMapper purchaseCountMapper;


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

    private PurchaseCount preparePurchaseCount(){
        PurchaseCount purchaseCount = new PurchaseCount();
        purchaseCount.setTotal(10);
        purchaseCount.setRemainingTotal(30);
        purchaseCount.setId(1L);
        purchaseCount.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        purchaseCount.setStartDate(ZonedDateTime.now());
        purchaseCount.setEndDate(ZonedDateTime.now().plusDays(3));
        return purchaseCount;
    }

    private PurchaseDTO preparePurchase(){
        PurchaseDTO purchase = new PurchaseDTO();
        purchase.setPaymentId(1L);
        purchase.setAdvertPackage(null);
        purchase.setId(1L);
        purchase.setPurchaseDate(ZonedDateTime.now());
        return purchase;
    }

    @Test
    void purchaseCount(){
        Mockito.when(purchaseCountRepository.getAllCurrentPurchaseCount(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"))).thenReturn(preparePurchaseCount());
        Mockito.when(purchaseCountRepository.save(preparePurchaseCount())).thenReturn(preparePurchaseCount());
        purchaseCountService.purchaseCount(preparePurchase(),UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
    }

    @Test
    void getPurchaseCount(){
        Mockito.when(purchaseCountRepository.getAllCurrentPurchaseCount(any())).thenReturn(preparePurchaseCount());
        Mockito.when(purchaseCountMapper.toDTO(any())).thenReturn(preparePurchaseCountDTO());
        PurchaseCountDTO purchaseCountDTO = purchaseCountService.getPurchaseCount(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertNotNull(purchaseCountDTO);
    }

    @Test
    void update(){
        Mockito.when(purchaseCountRepository.findById(anyLong())).thenReturn(java.util.Optional.of(preparePurchaseCount()));
        Mockito.when(purchaseCountMapper.toDTO(any())).thenReturn(preparePurchaseCountDTO());
        Mockito.when(purchaseCountRepository.save(preparePurchaseCount())).thenReturn(preparePurchaseCount());
        PurchaseCountDTO purchaseCountDTO = purchaseCountService.update(preparePurchaseCountDTO());
        assertNotNull(purchaseCountDTO);


    }

}
