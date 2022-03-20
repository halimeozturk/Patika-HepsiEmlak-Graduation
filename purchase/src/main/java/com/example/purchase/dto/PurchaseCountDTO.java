package com.example.purchase.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class PurchaseCountDTO extends BaseEntityDTO implements Serializable {
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private Integer total;
    private UUID userId;
    private Integer remainingTotal;
}
