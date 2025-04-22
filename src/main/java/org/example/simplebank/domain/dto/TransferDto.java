package org.example.simplebank.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Модель данных для перевода между счетами")
public record TransferDto(
        int pinCode,
        String toAccountNumber,
        BigDecimal amount) {
}