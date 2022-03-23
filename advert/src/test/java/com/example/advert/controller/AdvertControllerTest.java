package com.example.advert.controller;

import com.example.advert.dto.AdvertDTO;
import com.example.advert.enums.AdvertStatus;
import com.example.advert.model.Advert;
import com.example.advert.service.AdvertService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class AdvertControllerTest {

    @InjectMocks
    private AdvertController advertController;
    @Mock
    private AdvertService advertService;


    private List<Advert> prepareMockAdvertList(){
        List<Advert> adverts = new ArrayList<>();
        adverts.add(prepareMockAdvert());
        return adverts;
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

    private List<AdvertDTO> prepareMockAdvertDTOList(){
        List<AdvertDTO> advertDTOS = new ArrayList<>();
        advertDTOS.add(prepareMockAdvertDTO());
        return advertDTOS;
    }

    private AdvertDTO prepareMockAdvertDTO(){
        AdvertDTO advertDTO = new AdvertDTO();
        advertDTO.setId(1L);
        advertDTO.setAdvertNo(1984L);
        advertDTO.setActive(true);
        advertDTO.setAge(0);
        advertDTO.setBathRoom(2);
        advertDTO.setAdvertStatus(AdvertStatus.ACTIVE);
        advertDTO.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        return advertDTO;
    }

    @Test
    void create(){
        ResponseEntity res = advertController.create(prepareMockAdvertDTO(), UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertEquals(res.getStatusCodeValue(), 200);
    }

    @Test
    void getAllList(){
        ResponseEntity res = advertController.getAllList();
        assertEquals(res.getStatusCodeValue(), 200);
    }

    @Test
    void update(){
        ResponseEntity res = advertController.update(prepareMockAdvertDTO());
        assertEquals(res.getStatusCodeValue(), 200);
    }

    @Test
    void getAdvertByAdvertNo(){
        ResponseEntity res = advertController.getAdvertByAdvertNo(1L);
        assertEquals(res.getStatusCodeValue(), 200);
    }

    @Test
    void getById(){
        ResponseEntity res = advertController.getById(1L);
        assertEquals(res.getStatusCodeValue(), 200);
    }

    @Test
    void getAllListWithFilter(){
        Pageable pageable = PageRequest.of(0, 8);

        ResponseEntity res = advertController.getAllListWithFilter(any(pageable.getClass()),any(),any(),any(),any(),any(),any(),any(),any(),any(),any(),any(),any(),any(),any(),any(),any(),any(),any());
        assertEquals(res.getStatusCodeValue(), 200);
    }

}
