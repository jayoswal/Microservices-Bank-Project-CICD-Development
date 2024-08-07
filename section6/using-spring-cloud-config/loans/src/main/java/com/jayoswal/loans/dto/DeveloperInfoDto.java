package com.jayoswal.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loans")
@Getter
@Setter
public class DeveloperInfoDto{

    String environment;
    Map<String, String> developer;
    String email;
    List<String> contact;
}
