package com.example.payment.dto;

import com.example.payment.enums.PurchaseStatus;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class PurchaseDTO  {
    private PurchaseStatus purchaseStatus;
    private Long userId;
    private String userEmal;
    private Long advertPackageId;
    private ZonedDateTime purchaseDate;
}
