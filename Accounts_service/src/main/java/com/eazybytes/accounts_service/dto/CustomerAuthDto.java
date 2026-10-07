package com.eazybytes.accounts_service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAuthDto {
    @Size(min = 8, max = 13, message = "Password must be between 8 and 13 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,13}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;
    private long customerId;
    private boolean isVerified;
}
