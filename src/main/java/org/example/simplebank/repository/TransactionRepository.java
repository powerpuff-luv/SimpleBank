package org.example.simplebank.repository;

import org.example.simplebank.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccountIdOrToAccountId(Long fromAccount_id, Long toAccount_id);
//    List<Transaction> findByAccountId(Long accountId);
}

