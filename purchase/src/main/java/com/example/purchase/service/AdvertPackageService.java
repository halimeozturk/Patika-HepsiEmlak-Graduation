package com.example.purchase.service;

import com.example.purchase.dto.AdvertPackageDTO;
import com.example.purchase.exception.GenericServiceException;
import com.example.purchase.mapper.AdvertPackageMapper;
import com.example.purchase.repository.AdvertPackageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvertPackageService {
    private final AdvertPackageMapper advertPackageMapper;
    private final AdvertPackageRepository advertPackageRepository;

    public AdvertPackageDTO getById(Long id){
        return advertPackageMapper.toDTO(advertPackageRepository.findById(id).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"Advert package not found")));
    }
}
