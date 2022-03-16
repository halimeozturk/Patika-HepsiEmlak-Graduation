package com.example.purchase.model;

import com.example.purchase.enums.PurchaseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Purchase extends BaseEntity implements Serializable {
    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne (fetch = FetchType.LAZY)
    private AdvertPackage advertPackage;

    private ZonedDateTime purchaseDate;
}
