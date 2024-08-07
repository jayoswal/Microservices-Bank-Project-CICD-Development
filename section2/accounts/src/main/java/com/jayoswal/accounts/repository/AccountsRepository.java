package com.jayoswal.accounts.repository;

import com.jayoswal.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    void deleteByCustomerId(Long customerId);
}
