package com.eazybytes.accounts_service.repo;

import com.eazybytes.accounts_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByNic(String nic);

    @Query("select  c from Customer  c where c.mobileNumber =:mobileNumber")
    Optional<Customer> findByMobileNumber(@Param("mobileNumber") String mobileNumber);

    @Query("select  c from Customer  c where c.email =:email")
    Optional<Customer> findByEmail(@Param("email") String email);
}
