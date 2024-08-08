package com.jayoswal.accounts.service;

import com.jayoswal.accounts.dto.CustomerDto;

public interface IAccountsService {

    /*
    *
    * @param customerDto - CustomerDto Object
    * @return Void
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    CustomerDto updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
