package com.example.advert.model;

import com.example.advert.enums.UserType;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Advert> favorite;
}
