package com.eazybytes.accounts_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Base {
    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime created_at;

    @Column(updatable = false)
    @CreatedBy
    private String create_by;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updated_at;

    @Column(insertable = false)
    @LastModifiedBy
    private String update_by;
}
