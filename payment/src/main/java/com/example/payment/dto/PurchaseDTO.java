package com.example.payment.dto;

import com.example.payment.enums.PurchaseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseDTO extends BaseEntityDTO implements Serializable {
    private PurchaseStatus purchaseStatus;
    private Long userId;
    private AdvertPackageDTO advertPackage;
    private ZonedDateTime purchaseDate;
}
