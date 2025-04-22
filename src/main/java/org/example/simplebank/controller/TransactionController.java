package org.example.simplebank.controller;

import lombok.RequiredArgsConstructor;
import org.example.simplebank.domain.dto.TransactionInfoDto;
import org.example.simplebank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // TODO добавить пагинацию
    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<List<TransactionInfoDto>> getAccountTransactions(
            @PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccount(accountNumber));
    }

    // TODO сделать метод для получения списка транзакций бенефициара
}