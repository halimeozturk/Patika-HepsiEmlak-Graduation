package com.example.purchase.service;

import com.example.purchase.dto.PurchaseCountDTO;
import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.exception.GenericServiceException;
import com.example.purchase.mapper.PurchaseCountMapper;
import com.example.purchase.model.PurchaseCount;
import com.example.purchase.repository.PurchaseCountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseCountService {
    private final PurchaseCountRepository purchaseCountRepository;
    private final PurchaseCountMapper purchaseCountMapper;

    @Transactional
    public void purchaseCount(PurchaseDTO purchaseDTO, UUID id){
        PurchaseCount currentPurchaseCount = purchaseCountRepository.getAllCurrentPurchaseCount(id);

       if(currentPurchaseCount == null){
           PurchaseCount purchaseCount = new PurchaseCount();
           purchaseCount.setUserId(id);
           purchaseCount.setStartDate(purchaseDTO.getPurchaseDate());
           purchaseCount.setEndDate(purchaseDTO.getPurchaseDate().plusDays(30));
           purchaseCount.setTotal(10);
           purchaseCount.setRemainingTotal(10);
           purchaseCountRepository.save(purchaseCount);
       }else{
            currentPurchaseCount.setEndDate(currentPurchaseCount.getEndDate().plusDays(30));
            currentPurchaseCount.setTotal(currentPurchaseCount.getTotal() + 10);
            currentPurchaseCount.setRemainingTotal(currentPurchaseCount.getRemainingTotal() + 10);
            purchaseCountRepository.save(currentPurchaseCount);
       }
    }

    public PurchaseCountDTO getPurchaseCount(UUID id){
        return purchaseCountMapper.toDTO(purchaseCountRepository.getAllCurrentPurchaseCount(id));
    }

    @Transactional
    public PurchaseCountDTO update(PurchaseCountDTO purchaseCountDTO){
        PurchaseCount purchaseCount = purchaseCountRepository.findById(purchaseCountDTO.getId()).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"Purchase count not found"));
        purchaseCount.setRemainingTotal(purchaseCountDTO.getRemainingTotal());
        return purchaseCountMapper.toDTO(purchaseCountRepository.save(purchaseCount));
    }
}
