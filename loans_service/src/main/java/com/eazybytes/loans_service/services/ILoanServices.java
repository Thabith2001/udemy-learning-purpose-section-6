package com.eazybytes.loans_service.services;

import com.eazybytes.loans_service.dto.LoansDto;

public interface ILoanServices {

    void createLoan(String nic);

    LoansDto fetchLoan(String nic);

    boolean updateLoan(LoansDto loansDto);

    boolean deleteLoan(String nic);
}
