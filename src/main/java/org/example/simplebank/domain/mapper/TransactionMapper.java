package org.example.simplebank.domain.mapper;

import org.example.simplebank.domain.dto.TransactionInfoDto;
import org.example.simplebank.domain.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    @Mapping(target = "fromAccountNumber", source = "fromAccount.number")
    @Mapping(target = "toAccountNumber", source = "toAccount.number")
    TransactionInfoDto transactionToTransactionInfoDto(Transaction transaction);
}
