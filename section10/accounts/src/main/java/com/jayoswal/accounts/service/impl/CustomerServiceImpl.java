package com.jayoswal.accounts.service.impl;

import com.jayoswal.accounts.dto.*;
import com.jayoswal.accounts.entity.Accounts;
import com.jayoswal.accounts.entity.Customer;
import com.jayoswal.accounts.exception.ResourceNotFoundException;
import com.jayoswal.accounts.mapper.AccountsMapper;
import com.jayoswal.accounts.repository.AccountsRepository;
import com.jayoswal.accounts.repository.CustomerRepository;
import com.jayoswal.accounts.service.ICardsFeignClient;
import com.jayoswal.accounts.service.ICustomerService;
import com.jayoswal.accounts.service.ILoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final ICardsFeignClient iCardsFeignClient;
    private final ILoansFeignClient iLoansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer's all details based on given mobile number
     */
    @Override
    public CustomerAllDetailsDto fetchAllDetails(String mobileNumber, String correlationID) {
        CustomerAllDetailsDto customerAllDetailsDto = new CustomerAllDetailsDto();

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customer id ", customer.getCustomerId().toString())
        );


        // TODO - exception if cards or loans ms gives error - getStatus
        // example - delete loans and try to fetch through fetchAllDetail
        ResponseEntity<CardsDto> cardsDto = iCardsFeignClient.fetchCardDetails(correlationID, mobileNumber);

        if(cardsDto != null) {
            customerAllDetailsDto.setCardsDto(cardsDto.getBody());
        }

        ResponseEntity<LoansDto> loansDto = iLoansFeignClient.fetchLoanDetails(correlationID, mobileNumber);

        if(loansDto != null) {
            customerAllDetailsDto.setLoansDto(loansDto.getBody());

        }
        // TODO - create a mapper



        customerAllDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        customerAllDetailsDto.setName(customer.getName());
        customerAllDetailsDto.setEmail(customer.getEmail());
        customerAllDetailsDto.setMobileNumber(customer.getMobileNumber());

        return customerAllDetailsDto;



    }
}
