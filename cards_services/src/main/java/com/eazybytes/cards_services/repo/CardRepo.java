package com.eazybytes.cards_services.repo;

import com.eazybytes.cards_services.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepo extends JpaRepository<Cards, Long> {

    Optional<Cards> findByCardNumber(String cardNumber);

    Optional<Cards> findByNic(String nic);
}
