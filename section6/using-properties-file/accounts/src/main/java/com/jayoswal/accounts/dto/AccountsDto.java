package com.jayoswal.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold account information"
)
public class AccountsDto {

    @Schema(
            description = "10 digit Account number", example = "9999999999"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Enter 10 digits account number")
    @NotEmpty(message = "account number cannot be empty")
    private Long accountNumber;

    @Schema(
            description = "Account Type", example = "Saving"
    )
    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;

    @Schema(
            description = "String-Branch Address", example = "12 NY"
    )
    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}
