package com.eazybytes.cards_services.model;

import com.eazybytes.cards_services.constants.CardTypes;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Cards extends Base{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long cardId;
    private String nic;
    private String cardNumber;
    @Enumerated(EnumType.STRING)
    private CardTypes cardType;
    private double totalLimit;
    private double usedAmount;
    private double availableAmount;
}
