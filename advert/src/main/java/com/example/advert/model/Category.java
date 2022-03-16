package com.example.advert.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity implements Serializable {
    private Long parent_id;
    private String name;
}
