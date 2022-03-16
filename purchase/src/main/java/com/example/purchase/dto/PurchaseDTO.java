package com.example.purchase.dto;

import com.example.purchase.enums.PurchaseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseDTO extends BaseEntityDTO implements Serializable {
    private PurchaseStatus purchaseStatus;
    private UserDTO user;
    private AdvertPackageDTO advertPackage;
    private ZonedDateTime purchaseDate;
}
