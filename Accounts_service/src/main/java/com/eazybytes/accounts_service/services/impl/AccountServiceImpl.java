package com.eazybytes.accounts_service.services.impl;

import com.eazybytes.accounts_service.constants.AccountConstants;
import com.eazybytes.accounts_service.constants.AccountsTypes;
import com.eazybytes.accounts_service.dto.AccountsDto;
import com.eazybytes.accounts_service.dto.CustomerDto;
import com.eazybytes.accounts_service.exceptions.CustomerAlreadyExistException;
import com.eazybytes.accounts_service.exceptions.ResourcesNotFoundException;
import com.eazybytes.accounts_service.mapper.AccountsMapper;
import com.eazybytes.accounts_service.mapper.CustomerMapper;
import com.eazybytes.accounts_service.model.Accounts;
import com.eazybytes.accounts_service.model.Customer;
import com.eazybytes.accounts_service.model.CustomerAuth;
import com.eazybytes.accounts_service.repo.AccountRepo;
import com.eazybytes.accounts_service.repo.CustomerAuthReo;
import com.eazybytes.accounts_service.repo.CustomerRepo;
import com.eazybytes.accounts_service.services.IAccountService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {


    private final AccountRepo accountRepo;
    private final CustomerRepo customerRepo;
    private final CustomerAuthReo authRepo;


    @Override
    public CustomerDto fetchByNic(String nic) {

        Customer customer = customerRepo.findByNic(nic).orElseThrow(() ->
                new ResourcesNotFoundException("Customer", "NIC Number", nic));

        Accounts accounts = accountRepo.findByCustomerId(customer.getCustomer_id()).orElseThrow(() ->
                new ResourcesNotFoundException("Account", "Customer Id", customer.getCustomer_id() + ""));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    @Override
    public void createAccount(CustomerDto customerDto, String password) {


        Optional<Customer> customer = customerRepo.findByMobileNumber(customerDto.getMobileNumber());
        if (customer.isPresent()) {
            throw new CustomerAlreadyExistException("THIS CUSTOMER ,MOBILE NUMBER IS ALREADY EXIST" + customerDto.getMobileNumber());
        }

        Optional<Customer> customer1 = customerRepo.findByEmail(customerDto.getEmail());
        if (customer1.isPresent()) {
            throw new CustomerAlreadyExistException("THIS CUSTOMER EMAIL ID IS ALREADY EXIST");
        }
        Customer customers = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer cus = customerRepo.save(customers);
        accountRepo.save(createAccounts(cus));
        authRepo.save(auth(password, new CustomerAuth(), customers.getCustomer_id(), cus.getCustomer_id() != 0));

    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdate = false;
        AccountsDto dto = customerDto.getAccountDto();
        if (dto != null) {
            Accounts accounts = accountRepo.findById(dto.getAccount_Number())
                    .orElseThrow(() -> new ResourcesNotFoundException(
                            "Accounts", "AccountNumber", dto.getAccount_Number() + ""));
            Accounts ac = AccountsMapper.mapToAccounts(accounts, customerDto.getAccountDto());
            accountRepo.save(ac);

            long cid = accounts.getCustomerId();
            Customer customer = customerRepo.findById(cid)
                    .orElseThrow(() -> new ResourcesNotFoundException(
                            "Customer", "Customer Id", cid + ""));

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepo.save(customer);
            isUpdate = true;
        }
        return isUpdate;
    }

    @Override
    public boolean deleteAccount(String nic) {

        Customer customer = customerRepo.findByNic(nic).orElseThrow(() ->
                new ResourcesNotFoundException("Customer", "NIC", nic));

        Long cid = customer.getCustomer_id();
        customerRepo.deleteById(cid);
        accountRepo.deleteByCustomerId(cid);
        authRepo.deleteByCustomerId(cid);
        return true;
    }


    private Accounts createAccounts(Customer customer) {
        long account_number = 100000000L + new Random().nextLong(900000000);
        return Accounts.builder()
                .account_Number(account_number)
                .accountTypes(AccountsTypes.SAVINGS)
                .branch(AccountConstants.ADDRESS)
                .customerId(customer.getCustomer_id())
                .build();
    }

    private CustomerAuth auth(String pass, CustomerAuth auth, long cid, boolean verification) {
        auth.setPassword(pass);
        auth.setCustomerId(cid);
        auth.setVerified(verification);
        return auth;
    }
}
