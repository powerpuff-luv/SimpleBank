package org.example.simplebank.domain.dto;

import org.example.simplebank.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionInfoDto(

        TransactionType action,

        BigDecimal amount,

        LocalDateTime timestamp,

        String fromAccountNumber,

        String toAccountNumber) {
}