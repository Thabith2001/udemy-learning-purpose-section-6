package com.eazybytes.loans_service.repo;

import com.eazybytes.loans_service.model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepo extends JpaRepository<Loans, Long> {
    Optional<Loans> findByNic(String nic);

    Optional<Loans> findByLoanNumber(String loanNumber);

}
