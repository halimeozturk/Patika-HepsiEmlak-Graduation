package com.example.advert.service;
import com.example.advert.client.PurchaseCountClient;
import com.example.advert.client.UserClient;
import com.example.advert.dto.AdvertDTO;
import com.example.advert.dto.PurchaseCountDTO;
import com.example.advert.dto.UserDTO;
import com.example.advert.enums.*;
import com.example.advert.mapper.AdvertMapper;
import com.example.advert.model.Advert;
import com.example.advert.repository.AdvertRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AdvertServiceTest {

    @InjectMocks
    private AdvertService advertService;
    @Mock
    private  AdvertMapper advertMapper;
    @Mock
    private  AdvertRepository advertRepository;
    @Mock
    private  UserClient userClient;
    @Mock
    private  PurchaseCountClient purchaseCountClient;
    @Mock
    private  QueueService queueService;
    @Mock
    Predicate predicate;
    @Mock
    CriteriaBuilder cb;
    @Mock
    EntityManagerFactory entityManagerFactory;
    @Mock
    EntityManager entityManager;
    @Mock
    CriteriaQuery<Advert> cq;
    @Mock
    private Pageable pageable;

    @BeforeEach
    void setup(){
        when(advertRepository.findAll())
                .thenReturn(prepareMockAdvertList());
        when(advertMapper.toDTOList(any()))
                .thenReturn(prepareMockAdvertDTOList());
        when(advertMapper.toDTO(any()))
                .thenReturn(prepareMockAdvertDTO());
        when(advertMapper.toEntity(any())).
                thenReturn(prepareMockAdvert());

    }

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

    private PurchaseCountDTO preparePurchaseCountDTO(){
        PurchaseCountDTO purchaseCountDTO = new PurchaseCountDTO();
        purchaseCountDTO.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        purchaseCountDTO.setTotal(10);
        purchaseCountDTO.setRemainingTotal(5);
        return purchaseCountDTO;
    }

    private UserDTO prepareUserDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        userDTO.setEmail("ozturkk.halimee@gmail.com");
        userDTO.setUsername("Halime");
        return userDTO;
    }

    @Test
    void create(){
        AdvertDTO advertDTO = prepareMockAdvertDTO();
        when(advertRepository.save(any()))
                .thenReturn(prepareMockAdvert());

        when(userClient.getUserById(any()))
                .thenReturn(new ResponseEntity<UserDTO>(prepareUserDTO(), HttpStatus.OK));

        when(purchaseCountClient.update(any()))
                .thenReturn(new ResponseEntity<PurchaseCountDTO>(preparePurchaseCountDTO(), HttpStatus.OK));

        when(purchaseCountClient.getPurchaseCount(any()))
                .thenReturn(new ResponseEntity<PurchaseCountDTO>(preparePurchaseCountDTO(), HttpStatus.OK));


        doNothing().when(queueService).changeStatus(any());

        AdvertDTO advert = advertService.create(advertDTO, UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertEquals(1984L,advert.getAdvertNo());
        verify(queueService).changeStatus(any());
        verify(purchaseCountClient).getPurchaseCount(any());
        verify(purchaseCountClient).update(any());
        verify(userClient).getUserById(any());
    }


    @Test
    void getAllAdvertTest() {
        List<AdvertDTO> allAdvert = advertService.getAllList();
        assertNotNull(allAdvert);
        assertThat(allAdvert.size()).isNotZero();
    }

    @Test
    void getAdvertById(){
        when(advertRepository.findById(any())).thenReturn(java.util.Optional.of(prepareMockAdvert()));
        AdvertDTO advertDTO = advertService.getById(any());
        assertEquals(advertDTO.getId(),1L);
    }

    @Test
    void getAdvertByAdvertNo(){
        when(advertRepository.findByAdvertNo(any())).thenReturn(java.util.Optional.of(prepareMockAdvert()));
        AdvertDTO advertDTO = advertService.getAdvertByAdvertNo(any());
        assertEquals(advertDTO.getAdvertNo(),1984L);
    }

    @Test
    void update(){
        when(advertRepository.save(any())).thenReturn(prepareMockAdvert());
        when(advertRepository.findById(any())).thenReturn(java.util.Optional.of(prepareMockAdvert()));
        AdvertDTO advert = advertService.update(prepareMockAdvertDTO());
        assertEquals(advert.getId(), 1L);
    }

    @Test
    void checkPurchaseCount(){

        when(purchaseCountClient.update(any()))
                .thenReturn(new ResponseEntity<PurchaseCountDTO>(preparePurchaseCountDTO(), HttpStatus.OK));

        when(purchaseCountClient.getPurchaseCount(any()))
                .thenReturn(new ResponseEntity<PurchaseCountDTO>(preparePurchaseCountDTO(), HttpStatus.OK));
        advertService.checkPurchaseCount(any());
        verify(purchaseCountClient).getPurchaseCount(any());
        verify(purchaseCountClient).update(any());
    }

    @Test
    void writeQueue(){
        doNothing().when(queueService).changeStatus(any());
        advertService.writeQueue(any());
    }

    private PageImpl<AdvertDTO> prepareCriteriaMockAdvert(){
        Advert advert = new Advert();
        advert.setId(1L);
        advert.setAdvertNo(1984L);
        advert.setActive(true);
        advert.setAge(0);
        advert.setBathRoom(2);
        advert.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));

        return new PageImpl<AdvertDTO>(prepareMockAdvertDTOList(), pageable,5);
    }
    @Test
    void getAllListWithFilter(){
//        Page<Advert> page = new PageImpl<Advert>(prepareMockAdvertList(), PageRequest.of(6, 50), prepareMockAdvertList().size());
//        when(advertRepository.findAll((Specification<Advert>) any(), (Pageable) any())).thenReturn((Page<Advert>) prepareMockAdvert());
        Pageable pageable = PageRequest.of(0, 8);

        Page<AdvertDTO> page1 = advertService.getAllListWithFilter(any(pageable.getClass()), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
        assertEquals(page1,8);

    }
}
