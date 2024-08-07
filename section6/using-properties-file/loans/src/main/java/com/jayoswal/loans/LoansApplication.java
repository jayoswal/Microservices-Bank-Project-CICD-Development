package com.jayoswal.loans;

import com.jayoswal.loans.dto.DeveloperInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
/*@ComponentScans({ @ComponentScan("com.jayoswal.loans.controller") })
@EnableJpaRepositories("com.jayoswal.loans.repository")
@EntityScan("com.jayoswal.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "Bank's Loans microservice REST API Documentation",
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
@EnableConfigurationProperties(value = DeveloperInfoDto.class)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
