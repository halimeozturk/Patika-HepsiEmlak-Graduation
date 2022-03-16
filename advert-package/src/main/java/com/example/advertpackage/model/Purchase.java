package com.example.advertpackage.model;

import com.example.advertpackage.enums.PurchaseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
public class Purchase extends BaseEntity implements Serializable {
    private PurchaseStatus purchaseStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne (fetch = FetchType.LAZY)
    private AdvertPackage advertPackage;

    private ZonedDateTime purchaseDate;
}
