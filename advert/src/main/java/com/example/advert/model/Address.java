package com.example.advert.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity implements Serializable {
    private String province;
    private String district;
    private String fullAddress;

}
