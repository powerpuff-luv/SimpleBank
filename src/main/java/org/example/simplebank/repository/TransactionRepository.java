package org.example.simplebank.repository;

import org.example.simplebank.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.fromAccount.number = :accountNumber OR t.toAccount.number = :accountNumber " +
            "ORDER BY t.timestamp DESC")
    List<Transaction> findAllByAccount(@Param("accountNumber") String accountNumber);

//    @Query("SELECT t FROM Transaction t " +
//            "WHERE t.fromAccount.number = :accountNumber OR t.toAccount.number = :accountNumber " +
//            "ORDER BY t.timestamp DESC")
//    Page<Transaction> findAllByAccount(@Param("accountId") String accountNumber, Pageable pageable);
}

