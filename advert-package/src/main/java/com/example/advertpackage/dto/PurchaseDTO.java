package com.example.advertpackage.dto;

import com.example.advertpackage.enums.PurchaseStatus;
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
