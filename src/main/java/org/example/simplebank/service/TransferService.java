package org.example.simplebank.service;

import lombok.RequiredArgsConstructor;
import org.example.simplebank.domain.dto.TransferDto;
import org.example.simplebank.domain.exception.InsufficientFundsException;
import org.example.simplebank.domain.exception.InvalidPinCodeException;
import org.example.simplebank.domain.exception.UnauthorizedAccountAccessException;
import org.example.simplebank.domain.model.Account;
import org.example.simplebank.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

import static org.example.simplebank.domain.enums.TransactionType.TRANSFER;

@RequiredArgsConstructor
@Service
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    /**
     * TODO Добавить ответ на выполнение транзакции, заменить исключения на кастомные
     */
    @Transactional
    public void processTransfer(Long beneficiaryId, String fromAccountNumber, TransferDto transferDto) throws AccountNotFoundException {
        Account fromAccount = accountRepository.findByNumber(fromAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException(fromAccountNumber));
        System.out.println(transferDto.toAccountNumber());
        Account toAccount = accountRepository.findByNumber(transferDto.toAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException(transferDto.toAccountNumber()));

        validateTransfer(beneficiaryId, fromAccount, transferDto.pinCode(), transferDto.amount());

        performTransfer(fromAccount, toAccount, transferDto.amount());
    }

    /**
     * TODO добавить сообщения и обработки других возможных ситуаций
     */
    private void validateTransfer(Long beneficiaryId, Account fromAccount, int pinCode, BigDecimal amount) {
        if (fromAccount.getPinCode() != pinCode)
            throw new InvalidPinCodeException("");
        else if (!fromAccount.getBeneficiary().getId().equals(beneficiaryId))
            throw new UnauthorizedAccountAccessException("");
        else if (fromAccount.getBalance().compareTo(amount) < 0)
            throw new InsufficientFundsException("");
    }

    private void performTransfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        transactionService.recordTransaction(TRANSFER, fromAccount, toAccount, amount);
    }
}
