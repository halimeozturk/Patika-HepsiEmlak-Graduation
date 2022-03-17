package com.example.email.dto;

import com.example.email.model.BaseEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Currency;


@Data
@RequiredArgsConstructor
public class EmailDTO{
    private String email;
    private String message;
    private String packageName;
    private Integer advertNumber;
    private Integer duration;
    private Integer price;
    private String currency;

}
