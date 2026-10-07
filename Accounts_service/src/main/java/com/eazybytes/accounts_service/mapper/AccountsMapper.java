package com.eazybytes.accounts_service.mapper;

import com.eazybytes.accounts_service.dto.AccountsDto;
import com.eazybytes.accounts_service.model.Accounts;

public class AccountsMapper {

    public static Accounts mapToAccounts(Accounts acc, AccountsDto dto) {
        acc.setAccount_Number(dto.getAccount_Number());
        acc.setAccountTypes(dto.getAccountTypes());
        acc.setBranch(dto.getBranch());
        acc.setCustomerId(dto.getCustomer_id());
        return acc;
    }

    public static AccountsDto mapToAccountsDto(Accounts acc, AccountsDto dto) {
        dto.setAccount_Number(acc.getAccount_Number());
        dto.setAccountTypes(acc.getAccountTypes());
        dto.setBranch(acc.getBranch());
        dto.setCustomer_id(acc.getCustomerId());
        return dto;
    }
}
