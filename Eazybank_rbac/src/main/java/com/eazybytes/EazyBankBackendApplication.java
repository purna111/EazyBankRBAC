package com.eazybytes;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true,securedEnabled = true)
@OpenAPIDefinition(
        info=@Info(
                title = "EAZYBANK REST API documentation",
                description = "EAZYBANK REST API RBAC (Role Based Access Control) documentation",
                version = "v1",
                contact = @Contact(
                        name = "Purna",
                        url = "https://www.linkedin.com/in/purna-chandu-mucharla-001862250",
                        email="purnachandumucherla@gmail.com"
                ),
                license = @License(
                        name="Apache 2.0",
                        url = "https://www.google.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "EazyBanK  REST API documentation",
                url="https://springdoc.org"
        )
)
public class EazyBankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EazyBankBackendApplication.class, args);
    }

}
