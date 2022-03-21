package com.example.email.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@Data
@RequiredArgsConstructor
public class EmailDTO implements Serializable {
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
