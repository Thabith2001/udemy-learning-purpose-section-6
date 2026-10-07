package com.eazybytes.accounts_service.repo;

import com.eazybytes.accounts_service.model.CustomerAuth;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerAuthReo extends JpaRepository<CustomerAuth,Long> {

    @Transactional
    @Modifying
    void deleteByCustomerId(Long cid);
}
