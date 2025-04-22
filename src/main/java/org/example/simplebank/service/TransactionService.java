package org.example.simplebank.service;

import lombok.RequiredArgsConstructor;
import org.example.simplebank.domain.dto.TransactionInfoDto;
import org.example.simplebank.domain.mapper.TransactionMapper;
import org.example.simplebank.domain.model.Account;
import org.example.simplebank.domain.enums.TransactionType;
import org.example.simplebank.domain.model.Transaction;
import org.example.simplebank.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public void recordTransaction(TransactionType action, Account fromAccount, Account toAccount, BigDecimal sum) {
        Transaction transaction = Transaction.builder()
                .action(action)
                .amount(sum)
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .build();
        transactionRepository.save(transaction);
    }

    /**
     * TODO Обработать возвращение пустого списка
     */
    @Transactional(readOnly = true)
    public List<TransactionInfoDto> getTransactionsByAccount(String accountNumber) {
        return transactionRepository.findAllByAccount(accountNumber).stream()
                .map(transactionMapper::transactionToTransactionInfoDto)
                .collect(Collectors.toList());
    }

    // TODO реализовать пагинацию
//    @Transactional(readOnly = true)
//    public Page<TransactionInfoDto> getTransactionsByAccount(Long accountId, Pageable pageable) {
//        return transactionRepository.findAllByAccount(accountId, pageable)
//                .map(transactionMapper::transactionToTransactionInfoDto);
//    }
}
