package com.example.advert.dto;


import com.example.advert.enums.BuildState;
import com.example.advert.enums.BuildType;
import com.example.advert.enums.Currency;
import com.example.advert.enums.PublicationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdvertDTO extends BaseEntityDTO{
    private Long advertNo;
    private String title;
    private String description;
    private Integer putFordward;
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
    private PublicationType publicationType;
    private BuildType buildType;
    private BuildState buildState;
    private Boolean active;
    private String roomAndLivingRoom;
    private UserDTO owner;
    private List<ImageDTO> imageList;
    private AddressDTO address;
    private CategoryDTO mainCategory;
    private CategoryDTO subCategory;
}
