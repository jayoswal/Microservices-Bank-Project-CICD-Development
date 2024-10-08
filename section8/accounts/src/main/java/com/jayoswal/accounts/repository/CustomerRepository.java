package com.jayoswal.accounts.repository;

import com.jayoswal.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByMobileNumberOrEmail(String mobileNumber, String email);

    Optional<Customer> findByMobileNumber(String mobileNumber);
}
