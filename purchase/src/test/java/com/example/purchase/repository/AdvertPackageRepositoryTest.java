package com.example.purchase.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdvertPackageRepositoryTest {
    @Autowired
    private AdvertPackageRepository advertPackageRepository;


    @Test
    void existsById(){
        boolean b = advertPackageRepository.existsById(1L);
        Assertions.assertTrue(b);
    }


}
