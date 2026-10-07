package com.eazybytes.accounts_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ErrorResponse", description = "This Schema to hold ErrorResponse")
public class ErrorResponseDto {

    @Schema(description = "errorResponse api path")
    private String apiPath;
    @Schema(description = "errorResponse errorCode")
    private HttpStatus errorCode;
    @Schema(description = "errorResponse errorMessage")
    private String errorMessage;
    @Schema(description = "errorResponse errorTime")
    private LocalDateTime errorTime;
}
