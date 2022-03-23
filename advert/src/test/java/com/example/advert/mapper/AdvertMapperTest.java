package com.example.advert.mapper;

import com.example.advert.dto.AdvertDTO;
import com.example.advert.enums.AdvertStatus;
import com.example.advert.model.Advert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AdvertMapperTest {

    @InjectMocks
    private AdvertMapperImpl advertMapper;

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
    void toEntity(){
        Advert advert = advertMapper.toEntity(prepareMockAdvertDTO());
        assertNotNull(advert);
    }

    @Test
    void toDTO(){
        AdvertDTO advertDTO = advertMapper.toDTO(prepareMockAdvert());
        assertNotNull(advertDTO);
    }

    @Test
    void toEntityList(){
        List<Advert> adverts = advertMapper.toEntityList(prepareMockAdvertDTOList());
        assertNotNull(adverts);
    }

    @Test
    void toDTOList(){
        List<AdvertDTO> advertDTOS = advertMapper.toDTOList(prepareMockAdvertList());
        assertNotNull(advertDTOS);
    }
}
