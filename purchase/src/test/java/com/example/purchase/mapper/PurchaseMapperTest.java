package com.example.purchase.mapper;

import com.example.purchase.dto.AdvertPackageDTO;
import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.enums.Currency;
import com.example.purchase.model.AdvertPackage;
import com.example.purchase.model.Purchase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PurchaseMapperTest {

    @InjectMocks
    private PurchaseMapperImpl purchaseMapper;

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
    void toEntity(){
        Purchase purchase = purchaseMapper.toEntity(preparePurchaseDTO());
        assertNotNull(purchase);
    }

    @Test
    void toDTO(){
        PurchaseDTO purchaseDTO = purchaseMapper.toDTO(preparePurchase());
        assertNotNull(purchaseDTO);
    }

    @Test
    void toEntityList(){
        List<Purchase> purchaseList = purchaseMapper.toEntityList(purchaseDTOList());
        assertNotNull(purchaseList);
    }

    @Test
    void toDTOList(){
        List<PurchaseDTO> purchaseDTOList = purchaseMapper.toDTOList(purchaseList());
        assertNotNull(purchaseDTOList);
    }
}
