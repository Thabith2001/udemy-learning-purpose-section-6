package com.eazybytes.loans_service.services.impl;

import com.eazybytes.loans_service.Mapper.LoansMapper;
import com.eazybytes.loans_service.constants.LoansConstants;
import com.eazybytes.loans_service.dto.LoansDto;
import com.eazybytes.loans_service.exceptions.ResourceNotFoundException;
import com.eazybytes.loans_service.model.Loans;
import com.eazybytes.loans_service.repo.LoansRepo;
import com.eazybytes.loans_service.services.ILoanServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansService implements ILoanServices {

    private final LoansRepo loansRepo;

    @Override
    public void createLoan(String nic) {
        Optional<Loans> loans = loansRepo.findByNic(nic);
        if (loans.isPresent()) {
            throw new ResourceNotFoundException("Loan", "Nic", nic);
        }
        loansRepo.save(createNewLoan(nic));
    }

    @Override
    public LoansDto fetchLoan(String nic) {
        Loans loans = loansRepo.findByNic(nic).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "nic", nic));
        return LoansMapper.mapToLoansDto(loans, new LoansDto());
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loans = loansRepo.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepo.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String nic) {
        Loans loans = loansRepo.findByNic(nic).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "nic", nic));
        loansRepo.deleteById(loans.getLoanId());
        return true;
    }


    private Loans createNewLoan(String nic) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setNic(nic);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

}
