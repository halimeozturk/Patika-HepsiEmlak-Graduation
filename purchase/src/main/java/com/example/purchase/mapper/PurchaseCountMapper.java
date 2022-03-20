package com.example.purchase.mapper;

import com.example.purchase.dto.PurchaseCountDTO;
import com.example.purchase.model.PurchaseCount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseCountMapper extends BaseMapper<PurchaseCount, PurchaseCountDTO>{
}
