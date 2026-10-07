package com.eazybytes.cards_services;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Cards Services",
                description = "This api is created for learning purpose of Advanced springboot with microservices ",
                version = "v1",
                contact = @Contact(
                        name = "thabith",
                        email = "shahulhameed.thabith@gmail.com",
                        url = "https://thabith-portfolio.vercel.app/"
                ),
                license = @License(
                        name = "apache 2.0",
                        url = "http://localhost:8083/swagger-ui/index.html"
                )

        ), externalDocs = @ExternalDocumentation(
        description = "Easy Bank Cards Microservices Rest APIs Documentations",
        url = "http://localhost:8083/swagger-ui/index.html"
)
)
public class CardsServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsServicesApplication.class, args);
    }

}
