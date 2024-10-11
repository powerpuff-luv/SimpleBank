package org.example.simplebank.repository;

import org.example.simplebank.domain.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository  extends JpaRepository<Beneficiary, Long> {
}
