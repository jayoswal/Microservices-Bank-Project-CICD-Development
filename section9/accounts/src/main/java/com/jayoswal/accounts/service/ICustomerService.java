package com.jayoswal.accounts.service;

import com.jayoswal.accounts.dto.CustomerAllDetailsDto;

public interface ICustomerService {

    CustomerAllDetailsDto fetchAllDetails(String mobileNumber, String correlationID);
}
