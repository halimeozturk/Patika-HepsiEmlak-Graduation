package com.example.advert.repository;

import com.example.advert.model.Advert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdvertRepositoryTest {
    @Autowired
    private AdvertRepository advertRepository;

    @Test
    void findByAdvertNo(){
        Optional<Advert> advert = advertRepository.findByAdvertNo(1L);
        assertNotNull(advert);

    }
}
