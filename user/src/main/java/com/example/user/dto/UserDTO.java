package com.example.user.dto;


import com.example.user.enums.UserType;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO implements Serializable {
    private UUID id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ZonedDateTime creationDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ZonedDateTime modificationDate;
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
