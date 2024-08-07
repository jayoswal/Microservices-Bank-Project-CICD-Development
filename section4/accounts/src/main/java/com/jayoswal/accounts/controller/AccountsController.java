package com.jayoswal.accounts.controller;

import com.jayoswal.accounts.constants.AccountsConstants;
import com.jayoswal.accounts.dto.CustomerDto;
import com.jayoswal.accounts.dto.ErrorResponseDto;
import com.jayoswal.accounts.dto.ResponseDto;
import com.jayoswal.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Accounts Microservice",
        description = "API endpoints for CRUD"
)
public class AccountsController {

    private final IAccountsService iAccountsService;

    @Operation(
            summary = "Create Account REST endpoint",
            description = "Create a new Customer & Account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account creation success"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Get customer details REST endpoint",
            description = "Get details of Customer & Account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Account fetch success"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter 10 digits mobile number")
                                                               String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update customer details REST endpoint",
            description = "Update details of Customer & Account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account update success"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = iAccountsService.updateAccount(customerDto);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCustomerDto);
    }

    @Operation(
            summary = "Delete customer details REST endpoint",
            description = "Delete Customer & Account by customer mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account delete success"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter 10 digits mobile number")
                                                         String mobileNumber) {



        Boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);

        if (isDeleted) {
            ResponseDto responseDto = new ResponseDto(String.valueOf(HttpStatus.OK), "Deleted");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseDto);
        }
        else {
            ResponseDto responseDto = new ResponseDto(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), "Cannot Deleted");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDto);
        }
    }
}
