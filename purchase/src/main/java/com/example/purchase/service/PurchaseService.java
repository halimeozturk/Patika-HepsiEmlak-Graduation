package com.example.purchase.service;

import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.exception.GenericServiceException;
import com.example.purchase.mapper.PurchaseMapper;
import com.example.purchase.model.Purchase;
import com.example.purchase.repository.AdvertPackageRepository;
import com.example.purchase.repository.PurchaseRepository;
import com.example.purchase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseService {
    private final PurchaseMapper purchaseMapper;
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final AdvertPackageRepository advertPackageRepository;

    public List<PurchaseDTO> getAllList() {
        return purchaseMapper.toDTOList(purchaseRepository.findAll());
    }

    @Transactional
    public PurchaseDTO create(PurchaseDTO purchaseDTO) {
        checkNotInUse(purchaseDTO);
        return purchaseMapper.toDTO(purchaseRepository.save(purchaseMapper.toEntity(purchaseDTO)));
    }

    @Transactional
    public PurchaseDTO update(PurchaseDTO purchaseDTO) {
        getById(purchaseDTO.getId());
        checkNotInUse(purchaseDTO);
        return purchaseMapper.toDTO(purchaseRepository.save(purchaseMapper.toEntity(purchaseDTO)));
    }

    public PurchaseDTO getById(Long id){
        return purchaseMapper.toDTO(purchaseRepository.findById(id).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"Purchase not found")));
    }

    public void checkNotInUse(PurchaseDTO purchaseDTO){
        if(userRepository.existsById(purchaseDTO.getUser().getId())){
            throw new GenericServiceException(GenericServiceException.NOT_FOUND,"User not found " + purchaseDTO.getUser().getId());
        }
        if(advertPackageRepository.existsById(purchaseDTO.getAdvertPackage().getId())){
            throw new GenericServiceException(GenericServiceException.NOT_FOUND,"Package not found " + purchaseDTO.getAdvertPackage().getId());
        }
    }
}
