package com.example.advert.model;

import com.example.advert.enums.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Advert extends BaseEntity implements Serializable {
    private Long advertNo;
    private String title;
    private String description;
    private Integer putFordward;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Double price;
    private Integer room;
    private Integer livingRoom;
    private Integer age;
    private Integer bathRoom;
    private String floor;
    private Integer numberOfFloor;
    private Double netSquareMeters;
    private Double squareMeters;
    @Enumerated(EnumType.STRING)
    private PublicationType publicationType;
    @Enumerated(EnumType.STRING)
    private BuildType buildType;
    @Enumerated(EnumType.STRING)
    private BuildState buildState;
    private Boolean active;
    private String roomAndLivingRoom;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User owner;
    private UUID userId;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Image> imageList;
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private Category mainCategory;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    private Category subCategory;
    private AdvertStatus advertStatus;

}
