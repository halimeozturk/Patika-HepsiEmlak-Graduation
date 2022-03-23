package com.example.purchase.repository;

import com.example.purchase.model.Purchase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PurchaseRepositoryTest {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    void findByUserId(){
        Optional<List<Purchase>> purchaseList = purchaseRepository.findByUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertNotNull(purchaseList);
    }
}
