package org.example.simplebank.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.simplebank.domain.dto.AccountCreationDto;
import org.example.simplebank.domain.dto.AccountInfoDto;
import org.example.simplebank.domain.dto.AccountNumberDto;
import org.example.simplebank.domain.mapper.AccountMapper;
import org.example.simplebank.domain.model.Account;
import org.example.simplebank.domain.model.Beneficiary;
import org.example.simplebank.repository.AccountRepository;
import org.example.simplebank.repository.BeneficiaryRepository;
import org.example.simplebank.utils.AccountNumberGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BeneficiaryRepository beneficiaryRepository;
    private final AccountNumberGenerator accountNumberGenerator;
    private final AccountMapper accountMapper;

    /**
     * TODO заменить исключение на кастомное, перейти на маппер
     */
    public AccountNumberDto createAccount(Long beneficiaryId, AccountCreationDto accountCreationDto) throws EntityNotFoundException {
        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId)
                .orElseThrow(() -> new EntityNotFoundException("Beneficiary not found with id: " + beneficiaryId));
        Account account = Account.builder()
                .beneficiary(beneficiary)
                .pinCode(accountCreationDto.pinCode())
                .balance(accountCreationDto.balance())
                .number(generateUniqueAccountNumber())
                .build();
        Account savedAccount = accountRepository.save(account);
        return accountMapper.accountToAccountIdDto(savedAccount);
    }

    public List<AccountInfoDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(accountMapper::accountToAccountInfoDto)
                .collect(Collectors.toList());
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = accountNumberGenerator.generateRandomAccountNumber();
        } while (accountRepository.existsByNumber(accountNumber));
        return accountNumber;
    }
}

