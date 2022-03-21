package com.example.purchase.service;

import com.example.purchase.client.UserClient;
import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.exception.GenericServiceException;
import com.example.purchase.mapper.PurchaseMapper;
import com.example.purchase.model.Purchase;
import com.example.purchase.repository.AdvertPackageRepository;
import com.example.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseService {
    private final PurchaseMapper purchaseMapper;
    private final PurchaseRepository purchaseRepository;
    private final AdvertPackageRepository advertPackageRepository;
    private final UserClient userClient;
    private final PurchaseCountService purchaseCountService;

    public List<PurchaseDTO> getAllList() {
        return purchaseMapper.toDTOList(purchaseRepository.findAll());
    }

    @Transactional
    public PurchaseDTO create(PurchaseDTO purchaseDTO, UUID id) {
        checkNotInUse(purchaseDTO,id);
        purchaseCountService.purchaseCount(purchaseDTO,id);
        purchaseDTO.setUserId(id);
        Purchase purchase = purchaseRepository.save(purchaseMapper.toEntity(purchaseDTO));

        return purchaseMapper.toDTO(purchase);
    }

    @Transactional
    public PurchaseDTO update(PurchaseDTO purchaseDTO,UUID id) {
        getById(purchaseDTO.getId());
        checkNotInUse(purchaseDTO,id);
        return purchaseMapper.toDTO(purchaseRepository.save(purchaseMapper.toEntity(purchaseDTO)));
    }

    public PurchaseDTO getById(Long id){
        return purchaseMapper.toDTO(purchaseRepository.findById(id).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"Purchase not found")));
    }

    public PurchaseDTO getByUserId(UUID id){
        return purchaseMapper.toDTO(purchaseRepository.findByUserId(id).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"Purchase not found")));
    }

    public void checkNotInUse(PurchaseDTO purchaseDTO,UUID id){
        if(!userClient.existsUser(id)){
            throw new GenericServiceException(GenericServiceException.NOT_FOUND,"User not found " + id);
        }
        if(!advertPackageRepository.existsById(purchaseDTO.getAdvertPackage().getId())){
            throw new GenericServiceException(GenericServiceException.NOT_FOUND,"Package not found " + purchaseDTO.getAdvertPackage().getId());
        }
    }


}
