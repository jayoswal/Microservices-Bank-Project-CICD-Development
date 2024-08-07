package com.jayoswal.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record DeveloperInfoDto(
        String environment,
        Map<String, String> developer,
        String email,
        List<String> contact
) {
}
