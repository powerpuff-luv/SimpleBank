package org.example.simplebank.domain.dto;

import java.math.BigDecimal;

public class AccountCreationDto {

    private int pinCode;

    private BigDecimal balance;

    public int getPinCode() {
        return pinCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
