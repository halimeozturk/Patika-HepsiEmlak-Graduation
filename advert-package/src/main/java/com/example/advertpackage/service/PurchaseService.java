package com.example.advertpackage.service;

import com.example.advertpackage.dto.PurchaseDTO;
import com.example.advertpackage.exception.GenericServiceException;
import com.example.advertpackage.mapper.PurchaseMapper;
import com.example.advertpackage.repository.PurchaseRepository;
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

    public List<PurchaseDTO> getAllList() {
        return purchaseMapper.toDTOList(purchaseRepository.findAll());
    }

    @Transactional
    public PurchaseDTO create(PurchaseDTO purchaseDTO) {
        return purchaseMapper.toDTO(purchaseRepository.save(purchaseMapper.toEntity(purchaseDTO)));
    }

    @Transactional
    public PurchaseDTO update(PurchaseDTO purchaseDTO) {
        getById(purchaseDTO.getId());
        return purchaseMapper.toDTO(purchaseRepository.save(purchaseMapper.toEntity(purchaseDTO)));
    }

    public PurchaseDTO getById(Long id){
        return purchaseMapper.toDTO(purchaseRepository.findById(id).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"Purchase not found")));
    }
}
