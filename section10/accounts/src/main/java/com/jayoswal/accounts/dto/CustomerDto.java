package com.jayoswal.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold customer and account information"
)
public class CustomerDto {

    @Schema(
            name = "name",
            description = "Name of Customer, length 5 to 30", example = "JayOswal"
    )
    @NotEmpty(message = "Name cannot be empty.")
    @Size(min = 5, max = 30, message = "Name should be of length 5 to 30")
    private String name;

    @Schema(
            description = "Email of Customer", example = "mail@mail.com"
    )
    @NotEmpty(message = "Email cannot be empty.")
    @Email(message = "Invalid Email.")
    private String email;

    @Schema(
            description = "10 Digits mobile number of Customer", example = "9999999999"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter 10 digits mobile number")
    @NotEmpty(message = "Mobile number cannot be empty")
    private String mobileNumber;

    // TODO - how to validate this while update?
    @Schema(
            description = "Account Details of Customer"
    )
    private AccountsDto accountsDto;
}
