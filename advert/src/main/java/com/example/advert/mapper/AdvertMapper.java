package com.example.advert.mapper;


import com.example.advert.dto.AdvertDTO;
import com.example.advert.model.Advert;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",uses = {UserMapper.class,AddressMapper.class})
public interface AdvertMapper extends BaseMapper<Advert, AdvertDTO>{
}
