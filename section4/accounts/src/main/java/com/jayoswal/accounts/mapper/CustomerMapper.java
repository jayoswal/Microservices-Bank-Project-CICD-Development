package com.jayoswal.accounts.mapper;

import com.jayoswal.accounts.dto.AccountsDto;
import com.jayoswal.accounts.dto.CustomerDto;
import com.jayoswal.accounts.entity.Customer;

public class CustomerMapper {

    // Security point of view we can have custom rules like mask mobile number etc
    // which is not possible in 3rd party libraries like model mapper etc

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setName(customer.getName());
        return customerDto;
    }

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto, AccountsDto accountsDto) {
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setName(customer.getName());
        customerDto.setAccountsDto(accountsDto);
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setName(customerDto.getName());

        return customer;
    }
}
