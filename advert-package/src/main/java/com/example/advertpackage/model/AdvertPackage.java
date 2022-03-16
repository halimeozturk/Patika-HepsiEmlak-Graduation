package com.example.advertpackage.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
public class AdvertPackage extends BaseEntity implements Serializable {
    private String name;
    private String advertNumber;
    private String duration;
    private String price;
}
