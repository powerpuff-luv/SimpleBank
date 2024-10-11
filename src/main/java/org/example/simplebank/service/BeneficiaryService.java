package org.example.simplebank.service;

import org.example.simplebank.domain.dto.BeneficiaryCreationDto;
import org.example.simplebank.domain.dto.BeneficiaryIdDto;
import org.example.simplebank.repository.BeneficiaryRepository;
import org.example.simplebank.domain.model.Beneficiary;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;

    public BeneficiaryService(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }

    public BeneficiaryIdDto createBeneficiary(BeneficiaryCreationDto beneficiaryCreationDto) {
        Beneficiary beneficiary = new Beneficiary(beneficiaryCreationDto.getName());
        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
//        return mapper.beneficiaryToBeneficiaryIdDto(savedBeneficiary);
        BeneficiaryIdDto beneficiaryIdDto = new BeneficiaryIdDto();
        beneficiaryIdDto.setBeneficiaryId(savedBeneficiary.getId());
        return beneficiaryIdDto;
    }
}
