package com.example.payment.dto;


import com.example.payment.enums.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdvertPackageDTO extends BaseEntityDTO implements Serializable {
    private String name;
    private Integer advertNumber;
    private Integer duration;
    private Integer price;
    private Currency currency;
}
