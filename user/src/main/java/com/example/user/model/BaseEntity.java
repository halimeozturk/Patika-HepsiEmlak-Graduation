package com.example.user.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    @CreatedDate
    @Column(updatable = false, nullable = true)
    private ZonedDateTime creationDate;

    @LastModifiedDate
    @Column(nullable = true)
    private ZonedDateTime modificationDate;
}
