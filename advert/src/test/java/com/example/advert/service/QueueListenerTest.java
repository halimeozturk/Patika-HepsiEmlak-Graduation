package com.example.advert.service;

import com.example.advert.model.Advert;
import com.example.advert.repository.AdvertRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class QueueListenerTest {
    @InjectMocks
    private QueueListener queueListener;

    @Mock
    private  AdvertRepository advertRepository;

    @Test
    void getQueueMessage(){
        Mockito.when(advertRepository.findById(1L)).thenReturn(java.util.Optional.of(prepareMockAdvert()));
        Mockito.when(advertRepository.save(prepareMockAdvert())).thenReturn(prepareMockAdvert());
        queueListener.getQueueMessage(1L);
    }

    private Advert prepareMockAdvert(){
        Advert advert = new Advert();
        advert.setId(1L);
        advert.setAdvertNo(1984L);
        advert.setActive(true);
        advert.setAge(0);
        advert.setBathRoom(2);
        advert.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        return advert;
    }
}
