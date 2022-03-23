package com.example.purchase.service;


import com.example.purchase.client.UserClient;
import com.example.purchase.dto.AdvertPackageDTO;
import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.enums.Currency;
import com.example.purchase.exception.GenericServiceException;
import com.example.purchase.mapper.PurchaseMapper;
import com.example.purchase.model.AdvertPackage;
import com.example.purchase.model.Purchase;
import com.example.purchase.repository.AdvertPackageRepository;
import com.example.purchase.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PurchaseServiceTest {

    @InjectMocks
    private PurchaseService purchaseService;

    @Mock
    private PurchaseMapper purchaseMapper;
    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private AdvertPackageRepository advertPackageRepository;
    @Mock
    private UserClient userClient;
    @Mock
    private PurchaseCountService purchaseCountService;

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
        doNothing().when(purchaseCountService).purchaseCount(any(),any());
        Mockito.when(purchaseRepository.save(any())).thenReturn(preparePurchase());
        Mockito.when(purchaseMapper.toDTO(any())).thenReturn(preparePurchaseDTO());
        Mockito.when(userClient.existsUser(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"))).thenReturn(true);
        Mockito.when(advertPackageRepository.existsById(any())).thenReturn(true);
        assertDoesNotThrow(() -> {
            PurchaseDTO purchaseDTO = purchaseService.create(preparePurchaseDTO(),UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
            assertNotNull(purchaseDTO);
        });
    }

    @Test
    void update(){
        Mockito.when(purchaseRepository.save(any())).thenReturn(preparePurchase());
        Mockito.when(purchaseMapper.toDTO(any())).thenReturn(preparePurchaseDTO());
        Mockito.when(purchaseRepository.findById(any())).thenReturn(java.util.Optional.of(preparePurchase()));
        Mockito.when(userClient.existsUser(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"))).thenReturn(true);
        Mockito.when(advertPackageRepository.existsById(any())).thenReturn(true);
        assertDoesNotThrow(() -> {
            PurchaseDTO purchaseDTO = purchaseService.update(preparePurchaseDTO(),UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
            assertNotNull(purchaseDTO);
        });


    }

    @Test
    void getById(){
        Mockito.when(purchaseMapper.toDTO(any())).thenReturn(preparePurchaseDTO());
        Mockito.when(purchaseRepository.findById(any())).thenReturn(java.util.Optional.of(preparePurchase()));
        PurchaseDTO purchaseDTO = purchaseService.getById(any());
        assertNotNull(purchaseDTO);
    }

    @Test
    void getByUserId(){
        Mockito.when(purchaseMapper.toDTOList(any())).thenReturn(purchaseDTOList());
        Mockito.when(purchaseRepository.findByUserId(any())).thenReturn(java.util.Optional.of(purchaseList()));
        List<PurchaseDTO> purchaseDTOList = purchaseService.getByUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertNotNull(purchaseDTOList);
    }
}
