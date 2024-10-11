package org.example.simplebank.domain.dto;

import org.example.simplebank.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionInfoDto {

    private TransactionType action;

    private BigDecimal amount;

    private LocalDateTime timestamp;

    private String fromAccountNumber;

    private String toAccountNumber;

    public void setAction(TransactionType action) {
        this.action = action;
    }

    public TransactionType getAction() {
        return action;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }
}
