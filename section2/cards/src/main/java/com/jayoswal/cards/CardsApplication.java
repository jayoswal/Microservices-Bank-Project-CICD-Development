package com.jayoswal.cards;

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
				title = "Cards Microservice REST API Documentation",
				description = "Bank's Cards Service Documentation",
				version = "v1",
				contact = @Contact(
						name = "Jay Oswal",
						email = "mail@mail.com",
						url = "www.google.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "www.google.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Technical Architecture Diagram",
				url = "www.google.com"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
