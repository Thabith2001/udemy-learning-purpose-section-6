package com.eazybytes.accounts_service.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Response", description = "This Schema to hold Success Response")
public class ResponseDto {
    @Schema(description = "success Response", example = "200")
    private String statusCode;
    @Schema(description = "success Response Message", example = "HttpStatus OK")
    private String message;
}
