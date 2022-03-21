package com.example.email.dto;


import com.example.email.enums.Currency;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdvertPackageDTO implements Serializable {
    private String name;
    private Integer advertNumber;
    private Integer duration;
    private Integer price;
    private Currency currency;
    private Long id;
}
