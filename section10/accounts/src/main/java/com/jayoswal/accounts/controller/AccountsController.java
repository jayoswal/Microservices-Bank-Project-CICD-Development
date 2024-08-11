package com.jayoswal.accounts.controller;

import com.jayoswal.accounts.constants.AccountsConstants;
import com.jayoswal.accounts.dto.CustomerDto;
import com.jayoswal.accounts.dto.DeveloperInfoDto;
import com.jayoswal.accounts.dto.ErrorResponseDto;
import com.jayoswal.accounts.dto.ResponseDto;
import com.jayoswal.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Accounts Microservice",
        description = "API endpoints for CRUD"
)
public class AccountsController {

    private final IAccountsService iAccountsService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private DeveloperInfoDto developerInfoDto;

    @Operation(
            summary = "Create Account REST endpoint",
            description = "Create a new Customer & Account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account creation success"
    )
    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
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
    @GetMapping(value = "/fetch", produces = {MediaType.APPLICATION_JSON_VALUE})
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
    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
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
    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
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

    @Operation(
            summary = "Get Build Version from application props.",
            description = "Build Version is hardcoded in application properties"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "build version return success",
                    content = @Content(
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "1.0.0")
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping(value = "/build-version", produces = {MediaType.ALL_VALUE})
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity
                .ok(buildVersion);
    }

    @Operation(
            summary = "Get pwd property value from Environment",
            description = "Using Environment Interface"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK",
                    content = @Content(
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(
                                    name = "Example of pwd",
                                    value = "/usr/lib/app"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping(value = "/pwd")
    public ResponseEntity<String> getPwd() {
        return ResponseEntity
                .ok(environment.getProperty("PWD"));
    }

    @GetMapping(value = "/developer-info")
    public ResponseEntity<DeveloperInfoDto> getDeveloperInfo() {
        return ResponseEntity.ok(developerInfoDto);
    }
}
