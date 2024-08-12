package com.jayoswal.accounts.service.impl;

import com.jayoswal.accounts.dto.LoansDto;
import com.jayoswal.accounts.service.ILoansFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements ILoansFeignClient {
    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String correlationID, String mobileNumber) {
        return null;
    }
}
