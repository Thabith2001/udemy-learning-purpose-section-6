package com.eazybytes.accounts_service.model;


import com.eazybytes.accounts_service.constants.AccountsTypes;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Accounts extends Base {

    @Id
    @Column(name = "account_number", updatable = false, nullable = false)
    private long account_Number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountsTypes accountTypes;

    @Column(nullable = false)
    private String branch;

    private long customerId;
}