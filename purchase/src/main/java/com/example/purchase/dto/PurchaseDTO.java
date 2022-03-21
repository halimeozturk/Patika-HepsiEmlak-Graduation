package com.example.purchase.dto;

import com.example.purchase.enums.PurchaseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseDTO extends BaseEntityDTO implements Serializable {
    private PurchaseStatus purchaseStatus;
    private AdvertPackageDTO advertPackage;
    private ZonedDateTime purchaseDate;
    private UUID userId;
    private Long paymentId;

}
