package com.example.advertpackage.mapper;

import com.example.advertpackage.dto.PurchaseDTO;
import com.example.advertpackage.model.Purchase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper extends BaseMapper<Purchase, PurchaseDTO>{
}
