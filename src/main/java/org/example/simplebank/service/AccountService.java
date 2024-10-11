package org.example.simplebank.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.simplebank.domain.dto.AccountCreationDto;
import org.example.simplebank.domain.dto.AccountInfoDto;
import org.example.simplebank.domain.dto.AccountNumberDto;
import org.example.simplebank.domain.model.Account;
import org.example.simplebank.domain.model.Beneficiary;
import org.example.simplebank.repository.AccountRepository;
import org.example.simplebank.repository.BeneficiaryRepository;
import org.example.simplebank.utils.AccountNumberGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BeneficiaryRepository beneficiaryRepository;
    private final AccountNumberGenerator accountNumberGenerator;

    public AccountService(AccountRepository accountRepository, BeneficiaryRepository beneficiaryRepository, AccountNumberGenerator accountNumberGenerator) {
        this.accountRepository = accountRepository;
        this.beneficiaryRepository = beneficiaryRepository;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    public AccountNumberDto createAccount(Long beneficiaryId, AccountCreationDto accountCreationDto) throws EntityNotFoundException {
        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId).orElseThrow(() -> new EntityNotFoundException("Beneficiary with ID " + beneficiaryId + " not found"));
        Account account = new Account(beneficiary);
        account.setPinCode(accountCreationDto.getPinCode());
        account.setBalance(accountCreationDto.getBalance());
        account.setNumber(accountNumberGenerator.generateRandomAccountNumber());
        Account savedAccount = accountRepository.save(account);
//        return mapper.accountToAccountIdDto(account);
        AccountNumberDto accountNumberDto = new AccountNumberDto();
        accountNumberDto.setAccountNumber(savedAccount.getNumber());
        return accountNumberDto;
    }

    public List<AccountInfoDto> findAll() {
        List<Account> accountList = accountRepository.findAll();
        List<AccountInfoDto> accountInfoDtoList = new ArrayList<>();
        for (Account account : accountList) {
            AccountInfoDto accountInfoDto = new AccountInfoDto();
            accountInfoDto.setAccountNumber(account.getNumber());
            accountInfoDto.setBeneficiaryName(account.getBeneficiary().getName());
            accountInfoDto.setBalance(account.getBalance());
            accountInfoDtoList.add(accountInfoDto);
        }
        return accountInfoDtoList;
    }
}

