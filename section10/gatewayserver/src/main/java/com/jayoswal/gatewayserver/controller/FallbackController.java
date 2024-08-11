package com.jayoswal.gatewayserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/contactSupport")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<String> contactSupport() {
        return Mono.just("Something went wrong. Try again. Contact support if problem persists.");
    }
}
