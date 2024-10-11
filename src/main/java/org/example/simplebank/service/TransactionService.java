package org.example.simplebank.service;

import org.example.simplebank.domain.dto.TransactionInfoDto;
import org.example.simplebank.domain.model.Account;
import org.example.simplebank.domain.enums.TransactionType;
import org.example.simplebank.domain.model.Transaction;
import org.example.simplebank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void saveTransaction(TransactionType action, Account accountFrom, Account accountTo, BigDecimal sum) {
        Transaction transaction = new Transaction(action, sum, accountFrom, accountTo);
        transactionRepository.save(transaction);
    }

    public List<TransactionInfoDto> getAllTransactions(Long beneficiaryId, Long accountId) {
        List<Transaction> transactionList = transactionRepository.findByFromAccountIdOrToAccountId(accountId, accountId);
        List<TransactionInfoDto> transactionInfoDtoList = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            TransactionInfoDto transactionInfoDto = new TransactionInfoDto();
            transactionInfoDto.setAction(transaction.getAction());
            transactionInfoDto.setAmount(transaction.getAmount());
            transactionInfoDto.setTimestamp(transaction.getTimestamp());
            transactionInfoDto.setFromAccountNumber(transaction.getFromAccount().getNumber());
            transactionInfoDto.setToAccountNumber(transaction.getToAccount().getNumber());
            transactionInfoDtoList.add(transactionInfoDto);
        }
        return transactionInfoDtoList;
    }
}
