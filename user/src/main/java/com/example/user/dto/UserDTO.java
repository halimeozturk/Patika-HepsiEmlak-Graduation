package com.example.user.dto;


import com.example.user.enums.UserType;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;


@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseEntityDTO {
    private UserType userType;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
//    private String path;
//    private String phoneNumber;
//    private String officePhone;
//    private String vkn;

}
