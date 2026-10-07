package com.eazybytes.accounts_service;

import com.eazybytes.accounts_service.dto.AccountContactInformation;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountContactInformation.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts Services",
                description = "This api is created for learning purpose of Advanced springboot with microservices ",
                version = "v1",
                contact = @Contact(
                        name = "thabith",
                        email = "shahulhameed.thabith@gmail.com",
                        url = "https://thabith-portfolio.vercel.app/"
                ),
                license = @License(
                        name = "apache 2.0",
                        url = "http://localhost:8081/swagger-ui/index.html"
                )

        ), externalDocs = @ExternalDocumentation(
        description = "Easy Bank Accounts Microservices Rest APIs Documentations",
        url = "http://localhost:8081/swagger-ui/index.html"
)
)
public class AccountsServiceApplication {

    static void main(String[] args) {
        SpringApplication.run(AccountsServiceApplication.class, args);
    }

}
