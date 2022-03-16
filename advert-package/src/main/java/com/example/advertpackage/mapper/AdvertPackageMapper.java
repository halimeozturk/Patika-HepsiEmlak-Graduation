package com.example.advertpackage.mapper;

import com.example.advertpackage.dto.AdvertPackageDTO;
import com.example.advertpackage.model.AdvertPackage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvertPackageMapper extends BaseMapper<AdvertPackage, AdvertPackageDTO>{
}
