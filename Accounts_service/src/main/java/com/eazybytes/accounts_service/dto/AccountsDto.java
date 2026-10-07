package com.eazybytes.accounts_service.dto;

import com.eazybytes.accounts_service.constants.AccountsTypes;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Account", description = "This Schema to hold Account informations")
public class AccountsDto {

    @Schema(description = "Account number of customer's bank account", example = "1234567890")
    @NotEmpty(message = "account number cannot be empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "account number length cannot be less than 10 or high")
    private long account_Number;

    @Schema(description = "Account type of customer's bank account", example = "SAVINGS")
    @NotEmpty(message = "account type cannot be empty or null")
    private AccountsTypes accountTypes;

    @Schema(description = "Branch of customer's bank account", example = "COLOMBO")
    @NotEmpty(message = "account branch cannot be empty or null")
    private String branch;

    @Schema(description = "Customer ID of customer's bank account", example = "1123")
    @NotEmpty(message = "customer id cannot be null or empty")
    private long customer_id;
}
