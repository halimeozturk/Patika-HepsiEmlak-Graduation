package com.example.purchase.mapper;


import com.example.purchase.dto.AdvertPackageDTO;
import com.example.purchase.dto.PurchaseCountDTO;
import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.enums.Currency;
import com.example.purchase.model.AdvertPackage;
import com.example.purchase.model.Purchase;
import com.example.purchase.model.PurchaseCount;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PurchaseCountMapperTest {


    @InjectMocks
    private PurchaseCountMapperImpl purchaseCountMapper;

    private List<PurchaseCountDTO> purchaseCountDTOList(){
        List<PurchaseCountDTO> purchaseDTOList = new ArrayList<>();
        purchaseDTOList.add(preparePurchaseCountDTO());
        return purchaseDTOList;
    }


    private List<PurchaseCount> purchaseCountList(){
        List<PurchaseCount> purchaseList = new ArrayList<>();
        purchaseList.add(preparePurchaseCount());
        return purchaseList;
    }
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
    void toEntity(){
        PurchaseCount purchase = purchaseCountMapper.toEntity(preparePurchaseCountDTO());
        assertNotNull(purchase);
    }

    @Test
    void toDTO(){
        PurchaseCountDTO purchaseDTO = purchaseCountMapper.toDTO(preparePurchaseCount());
        assertNotNull(purchaseDTO);
    }

    @Test
    void toEntityList(){
        List<PurchaseCount> purchaseList = purchaseCountMapper.toEntityList(purchaseCountDTOList());
        assertNotNull(purchaseList);
    }

    @Test
    void toDTOList(){
        List<PurchaseCountDTO> purchaseDTOList = purchaseCountMapper.toDTOList(purchaseCountList());
        assertNotNull(purchaseDTOList);
    }
}
