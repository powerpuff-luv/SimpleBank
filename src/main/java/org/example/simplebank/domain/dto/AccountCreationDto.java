package org.example.simplebank.domain.dto;

import java.math.BigDecimal;

public record AccountCreationDto(
        int pinCode,

        BigDecimal balance) {
}