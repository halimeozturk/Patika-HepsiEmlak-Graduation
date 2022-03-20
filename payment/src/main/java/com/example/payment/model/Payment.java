package com.example.payment.model;

import com.example.payment.enums.Currency;
import com.example.payment.enums.PaymentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Payment extends BaseEntity implements Serializable {
    private UUID userId;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Long advertPackageId;
}
