package org.example.simplebank.domain.mapper;


import org.example.simplebank.domain.dto.AccountInfoDto;
import org.example.simplebank.domain.dto.AccountNumberDto;
import org.example.simplebank.domain.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountNumberDto accountToAccountIdDto(Account account);

    @Mapping(target = "accountNumber", source = "account.number")
    @Mapping(target = "beneficiaryName", expression = "java(account.getBeneficiary().getName())")
    AccountInfoDto accountToAccountInfoDto(Account account);
}
