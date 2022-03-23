package com.example.purchase.repository;


import com.example.purchase.model.PurchaseCount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PurchaseCountRepositoryTest {

    @Autowired
    private PurchaseCountRepository purchaseCountRepository;

    @Test
    void findAllByUserId(){
        List<PurchaseCount> allByUserId = purchaseCountRepository.findAllByUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertNotNull(allByUserId);
    }

    @Test
    void getAllCurrentPurchaseCount(){
        PurchaseCount purchaseCount = purchaseCountRepository.getAllCurrentPurchaseCount(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertNotNull(purchaseCount);
    }
}
