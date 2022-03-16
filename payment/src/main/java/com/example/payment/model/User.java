package com.example.payment.model;


import com.example.payment.enums.UserType;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
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
