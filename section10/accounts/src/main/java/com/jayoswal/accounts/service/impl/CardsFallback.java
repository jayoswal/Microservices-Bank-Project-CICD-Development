package com.jayoswal.accounts.service.impl;

import com.jayoswal.accounts.dto.CardsDto;
import com.jayoswal.accounts.service.ICardsFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements ICardsFeignClient {
    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationID, String mobileNumber) {
        return null;
    }
}
