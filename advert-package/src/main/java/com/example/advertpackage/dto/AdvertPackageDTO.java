package com.example.advertpackage.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdvertPackageDTO extends BaseEntityDTO implements Serializable {
    private String name;
    private String advertNumber;
    private String duration;
    private String price;
}
