package com.example.advert.service;



import com.example.advert.dto.AdvertDTO;
import com.example.advert.exception.GenericServiceException;
import com.example.advert.mapper.AdvertMapper;
import com.example.advert.repository.AdvertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvertService {
    private final AdvertMapper advertMapper;
    private final AdvertRepository advertRepository;

    public List<AdvertDTO> getAllList() {
        return advertMapper.toDTOList(advertRepository.findAll());
    }

    @Transactional
    public AdvertDTO create(AdvertDTO advertDTO) {
        advertDTO.setAdvertNo((long) (1 + new Random().nextInt(900000000)));
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


}
