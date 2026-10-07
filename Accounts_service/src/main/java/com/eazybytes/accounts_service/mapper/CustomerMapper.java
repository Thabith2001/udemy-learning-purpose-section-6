package com.eazybytes.accounts_service.mapper;

import com.eazybytes.accounts_service.dto.CustomerDto;
import com.eazybytes.accounts_service.model.Customer;

public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto dto, Customer customer) {

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setNic(dto.getNic());
        customer.setMobileNumber(dto.getMobileNumber());
        customer.setAddress(dto.getAddress());

        return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto dto) {

        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setNic(customer.getNic());
        dto.setMobileNumber(customer.getMobileNumber());
        dto.setAddress(customer.getAddress());


        return dto;
    }
}

