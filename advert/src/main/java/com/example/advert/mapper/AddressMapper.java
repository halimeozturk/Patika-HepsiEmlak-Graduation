package com.example.advert.mapper;



import com.example.advert.dto.AddressDTO;
import com.example.advert.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends BaseMapper<Address, AddressDTO>{
}
