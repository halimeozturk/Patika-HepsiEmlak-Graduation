package com.example.advert.dto;

import com.example.advert.enums.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseEntityDTO implements Serializable {
    private UserType userType;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Set<AdvertDTO> favorite;
}
