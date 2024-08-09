package com.jayoswal.accounts.controller;

import com.jayoswal.accounts.dto.CustomerAllDetailsDto;
import com.jayoswal.accounts.dto.ErrorResponseDto;
import com.jayoswal.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Customer Microservice",
        description = "API endpoints for fetchng customer all details"
)
public class CustomerController {

    private final ICustomerService iCustomerService;

    @Operation(
            summary = "Fetch Customer's All Details",
            description = "Endpoint to get customer's account, loans & cards details by mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "All details fetch success",
                    content = @Content(
                            schema = @Schema(implementation = CustomerAllDetailsDto.class)
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
    @GetMapping("/fetchAllDetails")
    public ResponseEntity<CustomerAllDetailsDto> fetchCustomerDetails(@RequestParam
                                                                      @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter 10 digits mobile number")
                                                                      String mobileNumber) {
        CustomerAllDetailsDto customerAllDetailsDto = iCustomerService.fetchAllDetails(mobileNumber);

        return ResponseEntity.ok(customerAllDetailsDto);
    }
}
