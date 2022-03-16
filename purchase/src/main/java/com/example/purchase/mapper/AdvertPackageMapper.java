package com.example.purchase.mapper;

import com.example.purchase.dto.AdvertPackageDTO;
import com.example.purchase.model.AdvertPackage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvertPackageMapper extends BaseMapper<AdvertPackage, AdvertPackageDTO>{
}
