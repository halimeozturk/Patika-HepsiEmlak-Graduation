package com.example.payment.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentLog extends BaseEntity implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment;
    private String errorMessage;
    private String status;
    private ZonedDateTime paymentDate;

}
