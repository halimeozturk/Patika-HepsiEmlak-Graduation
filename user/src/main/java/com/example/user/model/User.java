package com.example.user.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.example.user.enums.UserType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable {
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String password;
    private String firstName;
    private String lastName;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    @Column(unique = true)
    private String username;

}
