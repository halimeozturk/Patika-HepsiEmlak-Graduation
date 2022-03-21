package com.example.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessageDTO {
    private String email;
    private String message;
    private String packageName;
    private Long advertPackageId;
    private Integer duration;
    private Integer price;
    private String currency;
    private UUID userId;
    private String paymentStatus;


}
