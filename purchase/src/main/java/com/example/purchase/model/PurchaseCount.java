package com.example.purchase.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseCount extends BaseEntity implements Serializable {
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private Integer total;
    private UUID userId;
    private Integer remainingTotal;
}
