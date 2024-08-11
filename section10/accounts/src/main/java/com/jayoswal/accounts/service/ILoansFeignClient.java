package com.jayoswal.accounts.service;

import com.jayoswal.accounts.dto.LoansDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface ILoansFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(
            @RequestHeader("bank-correlation-id") String correlationID,
            @RequestParam String mobileNumber);
}
