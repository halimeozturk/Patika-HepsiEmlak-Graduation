package com.example.payment.dto;

import com.example.payment.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessageDTO {
    private String email;
    private String message;
    private String packageName;
    private Integer advertNumber;
    private Integer duration;
    private Integer price;
    private Currency currency;

}
