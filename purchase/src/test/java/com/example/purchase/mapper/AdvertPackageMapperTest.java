package com.example.purchase.mapper;


import com.example.purchase.dto.AdvertPackageDTO;
import com.example.purchase.dto.PurchaseCountDTO;
import com.example.purchase.enums.Currency;
import com.example.purchase.model.AdvertPackage;
import com.example.purchase.model.PurchaseCount;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AdvertPackageMapperTest {

    @InjectMocks
    private AdvertPackageMapperImpl advertPackageMapper;
    private List<AdvertPackageDTO> advertPackageDTOS(){
        List<AdvertPackageDTO> advertPackageDTOS = new ArrayList<>();
        advertPackageDTOS.add(prepareAdvertPackageDTO());
        return advertPackageDTOS;
    }


    private List<AdvertPackage> advertPackages(){
        List<AdvertPackage> advertPackages = new ArrayList<>();
        advertPackages.add(prepareAdvertPackage());
        return advertPackages;
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
        AdvertPackage advertPackage = advertPackageMapper.toEntity(prepareAdvertPackageDTO());
        assertNotNull(advertPackage);
    }

    @Test
    void toDTO(){
        AdvertPackageDTO advertPackageDTO = advertPackageMapper.toDTO(prepareAdvertPackage());
        assertNotNull(advertPackageDTO);
    }

    @Test
    void toEntityList(){
        List<AdvertPackage> advertPackages = advertPackageMapper.toEntityList(advertPackageDTOS());
        assertNotNull(advertPackages);
    }

    @Test
    void toDTOList(){
        List<AdvertPackageDTO> advertPackages = advertPackageMapper.toDTOList(advertPackages());
        assertNotNull(advertPackages);
    }
}
