package com.eazybytes.accounts_service.repo;

import com.eazybytes.accounts_service.model.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Accounts, Long> {

    @Query("select a from Accounts a where a.customerId=:customerId")
    Optional<Accounts> findByCustomerId(long customerId);


    @Transactional
    @Modifying
    void deleteByCustomerId(long customerId);
}
