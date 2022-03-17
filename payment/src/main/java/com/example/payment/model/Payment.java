package com.example.payment.model;

import com.example.payment.enums.Currency;
import com.example.payment.enums.PaymentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Payment extends BaseEntity implements Serializable {
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;

    private Long userId;
    private PaymentStatus paymentStatus;
    private Currency currency;
    private Long advertPackageId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private AdvertPackage advertPackage;


}
