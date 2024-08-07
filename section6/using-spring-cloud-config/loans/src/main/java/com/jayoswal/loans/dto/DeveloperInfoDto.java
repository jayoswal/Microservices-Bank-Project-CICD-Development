package com.jayoswal.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loans")
public record DeveloperInfoDto(
        String environment,
        Map<String, String> developer,
        String email,
        List<Integer> contact
) {
}
