package com.jayoswal.accounts.service.impl;

import com.jayoswal.accounts.constants.AccountsConstants;
import com.jayoswal.accounts.dto.AccountsDto;
import com.jayoswal.accounts.dto.CustomerDto;
import com.jayoswal.accounts.entity.Accounts;
import com.jayoswal.accounts.entity.Customer;
import com.jayoswal.accounts.exception.CustomerAlreadyExistsException;
import com.jayoswal.accounts.exception.ResourceNotFoundException;
import com.jayoswal.accounts.mapper.AccountsMapper;
import com.jayoswal.accounts.mapper.CustomerMapper;
import com.jayoswal.accounts.repository.AccountsRepository;
import com.jayoswal.accounts.repository.CustomerRepository;
import com.jayoswal.accounts.service.IAccountsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     * @return Void
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

        // check if mobile number
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumberOrEmail(customerDto.getMobileNumber(), customerDto.getEmail());
        if(existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with same email or mobile number already exists.");
        }


        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = null;
        try{
            savedCustomer = customerRepository.save(customer);
        }
        catch (Exception e) {
            new RuntimeException("E101 - cannot save Customer to db");
        }

        // Create Account for the newly created customer
        Accounts newAccount = new Accounts(
                savedCustomer.getCustomerId(),
                1000000000L + new Random().nextInt(900000000),
                AccountsConstants.SAVINGS,
                AccountsConstants.ADDRESS
        );

        try {
            accountsRepository.save(newAccount);
        }
        catch (Exception e) {
            new RuntimeException("E102 - cannot save Account to db");
        }



    }

    /**
     * @param mobileNumber
     * @return CustomerDto
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customer Id", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

           return customerDto;

    }

    @Override
    public CustomerDto updateAccount(CustomerDto newCustomerDto) {
       AccountsDto newAccountsDto = newCustomerDto.getAccountsDto();
       Accounts oldAccount = accountsRepository.findById(newAccountsDto.getAccountNumber()).orElseThrow(
               () -> new ResourceNotFoundException("Account", "account number", newAccountsDto.getAccountNumber().toString())
       );

       // update old account here
       AccountsMapper.mapToAccounts(newAccountsDto, oldAccount);
       accountsRepository.save(oldAccount);

       Long customerId = oldAccount.getCustomerId();
       Customer oldCustomer = customerRepository.findById(customerId).orElseThrow(
               () -> new ResourceNotFoundException("Customer", "customer id", customerId.toString())
       );

       // TODO - exception handle for DB operations - throw 417 and update swagger accordingly
       // update old customer here
        CustomerMapper.mapToCustomer(newCustomerDto, oldCustomer);
        Customer updatedCustomer = customerRepository.save(oldCustomer);

        // convert saved customer to saved customer dto and return
        CustomerDto savedCustomerDto = new CustomerDto();
        CustomerMapper.mapToCustomerDto(updatedCustomer, savedCustomerDto, newAccountsDto);

        return  savedCustomerDto;


    }

    @Override
    @Transactional
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber)
        );

        customer.getCustomerId();

        customerRepository.deleteById(customer.getCustomerId());

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customer id", customer.getCustomerId().toString())
        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());

        return true;

    }
}
