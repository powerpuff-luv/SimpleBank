package org.example.simplebank.controller;

import org.example.simplebank.domain.dto.BeneficiaryCreationDto;
import org.example.simplebank.domain.dto.BeneficiaryIdDto;
import org.example.simplebank.domain.model.Beneficiary;
import org.example.simplebank.service.BeneficiaryService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/beneficiaries")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @PostMapping
    public BeneficiaryIdDto createBeneficiary(@RequestBody BeneficiaryCreationDto beneficiaryCreationDto) {
        return beneficiaryService.createBeneficiary(beneficiaryCreationDto);
    }
}
