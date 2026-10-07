package com.eazybytes.accounts_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Customer", description = "This Schema to hold customer informations")
public class CustomerDto {

    @Schema(description = "customer's First name", example = "Jhon")
    @NotEmpty(message = "first name cannot be null or empty")
    @Size(min = 5, max = 30, message = "the length of customer first name should be between 5 and 30")
    private String firstName;

    @Schema(description = "customer's Last name", example = "Deo")
    @NotEmpty(message = "last name cannot be null or empty")
    @Size(min = 5, max = 30, message = "the length of customer last name should be between 5 and 30")
    private String lastName;

    @Schema(description = "customer's Number", example = "200025311189")
    @NotEmpty(message = "National Identity card number cannot be null or empty")
    @Size(min = 10, max = 12, message = "National Identity card number should be between 10 and 12 ")
    @Pattern(regexp = "^(?:[0-9]{9}[VXvx]|[0-9]{12})$", message = "enter a valid National Identity card number")
    private String nic;

    @Schema(description = "customer's Email", example = "example@test.com")
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "enter a valid email address")
    private String email;

    @Schema(description = "customer's Mobile number", example = "0771234567")
    @NotBlank(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "^07[0-9]{8}$", message = "Invalid Sri Lankan mobile number")
    private String mobileNumber;

    @Schema(description = "customer's Address", example = "No 123,New York ")
    @NotEmpty(message = "Address cannot be null or empty")
    private String address;

    private AccountsDto accountDto;


}
