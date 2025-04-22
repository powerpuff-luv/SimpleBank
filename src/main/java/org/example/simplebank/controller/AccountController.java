package org.example.simplebank.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.simplebank.domain.dto.AccountCreationDto;
import org.example.simplebank.domain.dto.AccountInfoDto;
import org.example.simplebank.domain.dto.AccountNumberDto;
import org.example.simplebank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    // TODO изменить эндпоинт на получение счетов бенефициара
    @GetMapping
    public ResponseEntity<List<AccountInfoDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // TODO убрать поле с балансом из dto, сделать эндпоинт с пополнением счета
    @PostMapping("/{beneficiaryId}")
    public ResponseEntity<AccountNumberDto> createAccount(
            @PathVariable Long beneficiaryId,
            @Valid @RequestBody AccountCreationDto accountCreationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                accountService.createAccount(beneficiaryId, accountCreationDto));
    }
}

