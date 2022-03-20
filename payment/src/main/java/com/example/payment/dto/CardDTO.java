package com.example.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private String number;
    private Integer exp_month;
    private Integer exp_year;
    private String cvc;
    private double amount;
    private Long advertPackageId;
    private String email;
    private String token;
    private UUID userId;
}
