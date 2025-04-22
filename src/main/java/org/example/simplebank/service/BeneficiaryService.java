package org.example.simplebank.service;

import lombok.RequiredArgsConstructor;
import org.example.simplebank.domain.dto.BeneficiaryCreationDto;
import org.example.simplebank.domain.dto.BeneficiaryIdDto;
import org.example.simplebank.domain.mapper.BeneficiaryMapper;
import org.example.simplebank.repository.BeneficiaryRepository;
import org.example.simplebank.domain.model.Beneficiary;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final BeneficiaryMapper beneficiaryMapper;

    /**
     * TODO перейти на uuid, добавить полей dto и модели, перейти на маппер
     */
    public BeneficiaryIdDto createBeneficiary(BeneficiaryCreationDto beneficiaryCreationDto) {
        Beneficiary beneficiary = new Beneficiary(beneficiaryCreationDto.name());
        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
        return beneficiaryMapper.beneficiaryToBeneficiaryIdDto(savedBeneficiary);
    }
}
