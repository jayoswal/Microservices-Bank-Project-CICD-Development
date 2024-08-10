package com.jayoswal.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;


@Component
public class FilterUtility {

    public static final String CORRELATION_ID_HEADER = "bank-correlation-id";

    public String getCorrelationId(HttpHeaders httpHeaders) {
        if(httpHeaders.get(CORRELATION_ID_HEADER) == null) {
            return null;
        }

        List<String> headersList = httpHeaders.get(CORRELATION_ID_HEADER);
        return headersList.stream().findFirst().get();
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange
    , String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID_HEADER, correlationId);
    }

    public String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }


}
