package com.eazybytes.accounts_service.services;

import com.eazybytes.accounts_service.dto.AccountsDto;
import com.eazybytes.accounts_service.dto.CustomerDto;


public interface IAccountService {

    CustomerDto fetchByNic(String nic);

    void createAccount(CustomerDto customerDto, String password);

    boolean deleteAccount(String nic);

   boolean updateAccount(CustomerDto customerDto);
}
