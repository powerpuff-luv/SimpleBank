package org.example.simplebank.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.simplebank.domain.dto.TransferDto;
import org.example.simplebank.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.security.auth.login.AccountNotFoundException;


@RequiredArgsConstructor
@RequestMapping("api/v1/transfers")
@RestController
public class TransferController {

    private final TransferService transferService;

    // TODO добавить ответ
    @PostMapping("/{beneficiaryId}/from/{accountNumber}")
    public ResponseEntity<Void> transfer(
            @PathVariable Long beneficiaryId,
            @PathVariable String accountNumber,
            @Valid @RequestBody TransferDto transferDto) throws AccountNotFoundException {

        transferService.processTransfer(beneficiaryId, accountNumber, transferDto);
        return ResponseEntity.ok().build();
    }
}