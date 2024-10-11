package org.example.simplebank.domain.enums;

public enum TransactionType {
    TRANSFER,  // Перевод средств
    DEPOSIT,   // Внесение средств || Пополнение
    WITHDRAW;   // Снятие средств

//    TRANSFER("Перевод"),
//    DEPOSIT("Пополнение"),
//    WITHDRAW("Снятие");
//
//    private final String action;
//
//    PaymentActions(String action) {
//        this.action = action;
//    }
}
