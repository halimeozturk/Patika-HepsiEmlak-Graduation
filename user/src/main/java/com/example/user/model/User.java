package com.example.user.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.example.user.enums.UserType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column
    protected UUID id;

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
