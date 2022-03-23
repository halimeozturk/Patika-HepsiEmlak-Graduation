package com.example.purchase.service;

import com.example.purchase.dto.AdvertPackageDTO;
import com.example.purchase.enums.Currency;
import com.example.purchase.mapper.AdvertPackageMapper;
import com.example.purchase.model.AdvertPackage;
import com.example.purchase.repository.AdvertPackageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AdvertPackageServiceTest {
    @InjectMocks
    private AdvertPackageService advertPackageService;
    @Mock
    private AdvertPackageMapper advertPackageMapper;
    @Mock
    private AdvertPackageRepository advertPackageRepository;



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
    void getById(){
        Mockito.when(advertPackageMapper.toDTO(prepareAdvertPackage())).thenReturn(prepareAdvertPackageDTO());
        Mockito.when(advertPackageRepository.findById(1L)).thenReturn(java.util.Optional.of(prepareAdvertPackage()));
        AdvertPackageDTO advertPackageDTO = advertPackageService.getById(1L);
        assertNotNull(advertPackageDTO);

    }
}
