package com.jayoswal.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

// This Record POJO is same as fields that are final and their setters do not exist
// so they cannot be changed in runtime after it is read
@ConfigurationProperties(prefix = "cards")
public record DeveloperInfoDto(
        String environment,
        Map<String, String> developer,
        String email,
        List<Integer> contact
) {
}
