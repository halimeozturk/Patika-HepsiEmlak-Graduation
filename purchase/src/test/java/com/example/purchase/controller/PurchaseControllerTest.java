package com.example.purchase.controller;


import com.example.purchase.dto.AdvertPackageDTO;
import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.enums.Currency;
import com.example.purchase.model.AdvertPackage;
import com.example.purchase.model.Purchase;
import com.example.purchase.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class PurchaseControllerTest {

    @InjectMocks
    private PurchaseController purchaseController;

    @Mock
    private PurchaseService purchaseService;

    private List<PurchaseDTO> purchaseDTOList(){
        List<PurchaseDTO> purchaseDTOList = new ArrayList<>();
        purchaseDTOList.add(preparePurchaseDTO());
        return purchaseDTOList;
    }


    private List<Purchase> purchaseList(){
        List<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(preparePurchase());
        return purchaseList;
    }

    private PurchaseDTO preparePurchaseDTO(){
        PurchaseDTO purchase = new PurchaseDTO();
        purchase.setPaymentId(1L);
        purchase.setAdvertPackage(prepareAdvertPackageDTO());
        purchase.setId(1L);
        purchase.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        purchase.setPurchaseDate(ZonedDateTime.now());
        return purchase;
    }

    private Purchase preparePurchase(){
        Purchase purchase = new Purchase();
        purchase.setPaymentId(1L);
        purchase.setAdvertPackage(prepareAdvertPackage());
        purchase.setId(1L);
        purchase.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        purchase.setPurchaseDate(ZonedDateTime.now());
        return purchase;
    }

    private AdvertPackageDTO prepareAdvertPackageDTO(){
        AdvertPackageDTO advertPackageDTO = new AdvertPackageDTO();
        advertPackageDTO.setId(1L);
        advertPackageDTO.setAdvertNumber(1);
        advertPackageDTO.setDuration(1);
        advertPackageDTO.setPrice(1);
        advertPackageDTO.setCurrency(Currency.TL);
        return advertPackageDTO;
    }

    private AdvertPackage prepareAdvertPackage(){
        AdvertPackage advertPackage = new AdvertPackage();
        advertPackage.setId(1L);
        advertPackage.setAdvertNumber(1);
        advertPackage.setDuration(1);
        advertPackage.setPrice(1);
        advertPackage.setCurrency(Currency.TL);
        return advertPackage;
    }
    @Test
    void create(){
        ResponseEntity res = purchaseController.create(preparePurchaseDTO(), UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertEquals(res.getStatusCodeValue(), 200);
    }

    @Test
    void update(){
        ResponseEntity res = purchaseController.update(preparePurchaseDTO(), UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertEquals(res.getStatusCodeValue(), 200);
    }


    @Test
    void getById(){
        ResponseEntity res = purchaseController.getById(1L);
        assertEquals(res.getStatusCodeValue(), 200);
    }

    @Test
    void getByUserId(){
        ResponseEntity res = purchaseController.getByUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertEquals(res.getStatusCodeValue(), 200);
    }



}
