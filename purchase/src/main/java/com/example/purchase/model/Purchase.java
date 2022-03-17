package com.example.purchase.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Purchase extends BaseEntity implements Serializable {
    private Long userId;

    @ManyToOne (fetch = FetchType.LAZY)
    private AdvertPackage advertPackage;

    private ZonedDateTime purchaseDate;
}
