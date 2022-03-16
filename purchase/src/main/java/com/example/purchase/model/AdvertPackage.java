package com.example.purchase.model;


import com.example.purchase.enums.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class AdvertPackage extends BaseEntity implements Serializable {
    private String name;
    private Integer advertNumber;
    private Integer duration;
    private Integer price;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
