package org.example.simplebank.domain.dto;

import java.math.BigDecimal;

public class TransferDto {
    private int pinCode;
    private String accountToNumber;
    private BigDecimal sum;

    public int getPinCode() {
        return pinCode;
    }

    public String getAccountToNumber() {
        return accountToNumber;
    }

    public BigDecimal getSum() {
        return sum;
    }
}
