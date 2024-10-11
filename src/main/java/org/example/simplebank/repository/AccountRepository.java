package org.example.simplebank.repository;


import org.example.simplebank.domain.model.Account;
import org.example.simplebank.domain.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByBeneficiary(Beneficiary beneficiary);

    Optional<Account> findByNumber(String number);
}
