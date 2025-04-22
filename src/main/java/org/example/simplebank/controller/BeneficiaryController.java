package org.example.simplebank.controller;

import lombok.RequiredArgsConstructor;
import org.example.simplebank.domain.dto.BeneficiaryCreationDto;
import org.example.simplebank.domain.dto.BeneficiaryIdDto;
import org.example.simplebank.service.BeneficiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beneficiaries")
@RestController
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @PostMapping
    public ResponseEntity<BeneficiaryIdDto> createBeneficiary(
            @RequestBody BeneficiaryCreationDto beneficiaryCreationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                beneficiaryService.createBeneficiary(beneficiaryCreationDto));
    }
}
