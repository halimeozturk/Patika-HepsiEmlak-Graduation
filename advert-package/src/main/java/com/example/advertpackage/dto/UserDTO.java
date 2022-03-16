package com.example.advertpackage.dto;

import com.example.advertpackage.enums.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseEntityDTO implements Serializable {
    private UserType userType;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
}
