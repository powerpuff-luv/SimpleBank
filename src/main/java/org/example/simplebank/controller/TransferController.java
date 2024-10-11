package org.example.simplebank.controller;

import jakarta.validation.Valid;
import org.example.simplebank.domain.dto.TransferDto;
import org.example.simplebank.domain.enums.TransactionType;
import org.example.simplebank.domain.model.Account;
import org.example.simplebank.repository.AccountRepository;
import org.example.simplebank.service.TransactionService;
import org.example.simplebank.service.TransferService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("api/v1")
public class TransferController {

    private final TransferService transferService;
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public TransferController(TransferService transferService, AccountRepository accountRepository, TransactionService transactionService) {
        this.transferService = transferService;
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @PostMapping("beneficiaries/{beneficiaryId}/transfer/{accountFromNumber}")
    public void transfer(@PathVariable Long beneficiaryId, @PathVariable String accountFromNumber, @Valid @RequestBody TransferDto transferDto) throws AccountNotFoundException {
        Account accountFrom = accountRepository.findByNumber(accountFromNumber)
                .orElseThrow(() -> new AccountNotFoundException(accountFromNumber));
        Account accountTo = accountRepository.findByNumber(transferDto.getAccountToNumber())
                .orElseThrow(() -> new AccountNotFoundException(transferDto.getAccountToNumber()));

        transferService.transfer(accountRepository, beneficiaryId, accountFrom, transferDto.getPinCode(), accountTo, transferDto.getSum());
        transactionService.saveTransaction(TransactionType.TRANSFER, accountFrom, accountTo, transferDto.getSum());
    }
}