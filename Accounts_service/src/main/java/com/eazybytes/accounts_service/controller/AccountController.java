package com.eazybytes.accounts_service.controller;

import com.eazybytes.accounts_service.constants.AccountConstants;
import com.eazybytes.accounts_service.dto.AccountContactInformation;
import com.eazybytes.accounts_service.dto.CustomerDto;
import com.eazybytes.accounts_service.dto.ErrorResponseDto;
import com.eazybytes.accounts_service.dto.ResponseDto;

import com.eazybytes.accounts_service.services.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;


@Tag(name = "CRUD rest APIs for accounts services of eazy bank ", description = "CRUD rest APIs in account service CREATE , READ , UPDATE , DELETE ")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private final IAccountService service;
    private final ObjectMapper mapper;
    private final Environment environment;
    private final AccountContactInformation information;

//    AccountController(IAccountService service, ObjectMapper mapper, Environment environment) {
//        this.service = service;
//        this.mapper = mapper;
//        this.environment = environment;
//    }

//    @Value("${build.version}")
//    private String buildVersion;

    // READ API
    @Operation(summary = "Read rest api for Eazy bank", description = "This read rest api will read the customer details and account details")

    @ApiResponses({@ApiResponse(responseCode = "200", description = "HttpStatus OK"), @ApiResponse(responseCode = "500", description = "HttpStatus INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetch(@Valid @Pattern(regexp = "^(?:[0-9]{9}[VXvx]|[0-9]{12})$", message = "enter a valid National Identity card number") @RequestParam String nic) {
        CustomerDto resp = service.fetchByNic(nic);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    // CREATE API
    @Operation(summary = "Create rest api for Eazy bank", description = "This create rest api will create customer and for account")
    @ApiResponse(responseCode = "201", description = "HttpStatus CREATED")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestPart("customer") String customerJson, @RequestPart("password") @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,13}$") String password) {
        CustomerDto customer = mapper.readValue(customerJson, CustomerDto.class);
        service.createAccount(customer, password);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
    }

    // DELETE API
    @Operation(summary = "Delete rest api for Easy bank ", description = "This delete rest api will delete the customer and account")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HttpStatus OK"), @ApiResponse(responseCode = "417", description = "Expectation Failed"), @ApiResponse(responseCode = "500", description = "HttpStatus INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)

    ))})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestParam @Pattern(regexp = "^(?:[0-9]{9}[VXvx]|[0-9]{12})$") String nic) {
        boolean isDeleted = service.deleteAccount(nic);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));

        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417));
        }
    }

    // UPDATE API
    @Operation(summary = "Update rest api for Easy bank ", description = "this update rest api will update the customer and account")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HttpStatus OK"), @ApiResponse(responseCode = "417", description = "Expectation Failed"), @ApiResponse(responseCode = "500", description = "HttpStatus INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdate = service.updateAccount(customerDto);
        if (isUpdate) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417));
        }
    }

    @Operation(summary = "Get Build Information", description = "Get build information that is deployed into accounts microservices")

    @ApiResponses({@ApiResponse(responseCode = "200", description = "HttpStatus OK"), @ApiResponse(responseCode = "500", description = "HttpStatus INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @GetMapping("/build-info")
    public ResponseEntity<String> buildInfo() {
        return ResponseEntity.ok().body(environment.getProperty("MAVEN_HOME"));
    }

    @Operation(summary = "Get Contact Information", description = "Get Contact information that is deployed into accounts microservices")

    @ApiResponses({@ApiResponse(responseCode = "200", description = "HttpStatus OK"), @ApiResponse(responseCode = "500", description = "HttpStatus INTERNAL_SERVER_ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInformation> contactInfo() {
        return ResponseEntity.ok().body(information);
    }
}
