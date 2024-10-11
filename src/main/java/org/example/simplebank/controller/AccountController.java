package org.example.simplebank.controller;

import jakarta.validation.Valid;
import org.example.simplebank.domain.dto.AccountCreationDto;
import org.example.simplebank.domain.dto.AccountInfoDto;
import org.example.simplebank.domain.dto.AccountNumberDto;
import org.example.simplebank.domain.dto.TransactionInfoDto;
import org.example.simplebank.service.AccountService;
import org.example.simplebank.service.TransactionService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@RestController
@RequestMapping("api/v1")
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/allAccounts")
    List<AccountInfoDto> findAll() {
        return accountService.findAll();
    }

    @PostMapping("/beneficiaries/{beneficiaryId}/accounts")
    public AccountNumberDto createAccount(@PathVariable Long beneficiaryId, @Valid @RequestBody AccountCreationDto accountCreationDto) {
        return accountService.createAccount(beneficiaryId, accountCreationDto);
    }

    @GetMapping("/beneficiaries/{beneficiaryId}/accounts/{accountId}/allTransactions")
    public List<TransactionInfoDto> getAllTransactions(@PathVariable Long beneficiaryId, @PathVariable Long accountId) {
        return transactionService.getAllTransactions(beneficiaryId, accountId);
    }

//    @GetMapping("/beneficiaries/{beneficiaryId}/accounts")
//    List<Account> findAllForBeneficiary(@PathVariable Long beneficiaryId) throws AccountNotFoundException {
//        return beneficiaryRepository.findById(beneficiaryId).orElseThrow(() -> new AccountNotFoundException(beneficiaryId.toString())).getAccountList();  // orElseThrow
//    }


//    @GetMapping("/beneficiaries/{beneficiaryId}/accounts/{accountId}")
//    Account findAccount(@PathVariable Long beneficiaryId, @PathVariable Long accountId) throws AccountNotFoundException {
//        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId.toString()));    // orElseThrow
//        if (Objects.equals(account.getBeneficiary().getId(), beneficiaryId)) {
//            return account;
//        }
//        throw new AccountNotFoundException(accountId.toString());
//    }
}

