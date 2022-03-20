package com.example.advert.service;



import com.example.advert.client.PurchaseCountClient;
import com.example.advert.client.UserClient;
import com.example.advert.dto.AdvertDTO;
import com.example.advert.dto.PurchaseCountDTO;
import com.example.advert.exception.GenericServiceException;
import com.example.advert.mapper.AdvertMapper;
import com.example.advert.repository.AdvertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvertService {
    private final AdvertMapper advertMapper;
    private final AdvertRepository advertRepository;
    private final UserClient userClient;
    private final PurchaseCountClient purchaseCountClient;

    public List<AdvertDTO> getAllList() {
        return advertMapper.toDTOList(advertRepository.findAll());
    }

    @Transactional
    public AdvertDTO create(AdvertDTO advertDTO,UUID id) {
        advertDTO.setAdvertNo((long) (1 + new Random().nextInt(900000000)));
        userClient.getUserById(id);
        checkPurchaseCount(id);
        return advertMapper.toDTO(advertRepository.save(advertMapper.toEntity(advertDTO)));
    }

    @Transactional
    public AdvertDTO update(AdvertDTO advertDTO) {
        getById(advertDTO.getId());
        return advertMapper.toDTO(advertRepository.save(advertMapper.toEntity(advertDTO)));
    }

    public AdvertDTO getAdvertByAdvertNo(Long advertNo){
        return advertMapper.toDTO(advertRepository.findByAdvertNo(advertNo).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"Advert not found")));
    }

    public AdvertDTO getById(Long id){
        return advertMapper.toDTO(advertRepository.findById(id).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"Advert not found")));
    }

    public void checkPurchaseCount(UUID id){
        ResponseEntity<PurchaseCountDTO> purchaseCount = purchaseCountClient.getPurchaseCount(id);
        if(purchaseCount.getBody() != null && purchaseCount.getBody().getRemainingTotal() > 0){
            purchaseCount.getBody().setRemainingTotal(purchaseCount.getBody().getRemainingTotal() - 1);
            purchaseCountClient.update(purchaseCount.getBody());
        }else{
            throw new GenericServiceException(GenericServiceException.CANT_CREATE_ADVERT,"\"You have don't the right to create advert.You must purchase package\"");
        }
    }


}
