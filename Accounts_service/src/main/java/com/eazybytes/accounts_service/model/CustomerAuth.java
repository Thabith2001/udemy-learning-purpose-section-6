package com.eazybytes.accounts_service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CustomerAuth extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authId;
    private long customerId;
    private String password;
    private boolean isVerified;
}
