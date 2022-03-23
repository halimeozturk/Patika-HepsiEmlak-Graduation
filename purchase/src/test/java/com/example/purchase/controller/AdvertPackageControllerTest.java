package com.example.purchase.controller;


import com.example.purchase.service.AdvertPackageService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AdvertPackageControllerTest {
    @InjectMocks
    private AdvertPackageController advertPackageController;

    @Mock
    private AdvertPackageService advertPackageService;


    @Test
    void getById(){
        ResponseEntity res = advertPackageController.getById(1L);
        assertEquals(res.getStatusCodeValue(), 200);
    }
}
