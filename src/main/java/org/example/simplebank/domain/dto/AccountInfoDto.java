package org.example.simplebank.domain.dto;

import java.math.BigDecimal;

public record AccountInfoDto(
        String accountNumber,

        String beneficiaryName,

        BigDecimal balance) {
}
