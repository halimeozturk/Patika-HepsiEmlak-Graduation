package com.example.purchase.mapper;

import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.model.Purchase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper extends BaseMapper<Purchase, PurchaseDTO>{
}
