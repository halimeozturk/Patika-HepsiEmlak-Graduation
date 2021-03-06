package com.example.payment.dto;

import com.example.payment.enums.PurchaseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseDTO extends BaseEntityDTO implements Serializable {
    private PurchaseStatus purchaseStatus;
    private UUID userId;
    private Long advertPackageId;
    private Long paymentId;
//    private ZonedDateTime purchaseDate;
}
