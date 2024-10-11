package org.example.simplebank.service;

import org.example.simplebank.domain.enums.TransactionType;
import org.example.simplebank.domain.model.Account;
import org.example.simplebank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    public void transfer(AccountRepository accountRepository, Long beneficiaryId, Account accountFrom, int pinCode, Account accountTo, BigDecimal sum) {
        if (accountFrom.getPinCode() != pinCode || !accountFrom.getBeneficiary().getId().equals(beneficiaryId)) {
            throw new IllegalArgumentException("Неверный PIN-код или некорректный бенефициар");
        }
        if (accountFrom.getBalance().compareTo(sum) < 0) {
            throw new IllegalArgumentException("Недостаточно средств");
        }

        accountFrom.setBalance(accountFrom.getBalance().subtract(sum));
        accountTo.setBalance(accountTo.getBalance().add(sum));

        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
    }
}
