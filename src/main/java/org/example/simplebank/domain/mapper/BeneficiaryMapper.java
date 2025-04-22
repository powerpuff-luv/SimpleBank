package org.example.simplebank.domain.mapper;

import org.example.simplebank.domain.dto.BeneficiaryIdDto;
import org.example.simplebank.domain.model.Beneficiary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BeneficiaryMapper {

    @Mapping(target = "beneficiaryId", source = "beneficiary.id")
    BeneficiaryIdDto beneficiaryToBeneficiaryIdDto(Beneficiary beneficiary);
}
