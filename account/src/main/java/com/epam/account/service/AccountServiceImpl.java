package com.epam.account.service;

import com.epam.account.entity.Account;
import com.epam.account.repo.AccountRepository;
import com.epam.library.dto.AccountDto;
import com.epam.library.exception.InsufficientException;
import com.epam.library.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = objectMapper.convertValue(accountDto , Account.class);
        account = accountRepository.save(account);
        return objectMapper.convertValue(account , AccountDto.class);
    }

    @Override
    public AccountDto getAccountById(long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("account not exist"));

        return objectMapper.convertValue(account , AccountDto.class);
    }

    @Override
    public AccountDto debitAccount(long accountId, long amount) {
        Account savedAccount = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("account not exist"));
        if(savedAccount.getBalance() <amount){
            throw new InsufficientException("insufficient balance to process");
        }
        savedAccount.setBalance(savedAccount.getBalance() - amount);
        savedAccount =accountRepository.save(savedAccount);
        return objectMapper.convertValue(savedAccount , AccountDto.class);
    }

    @Override
    public AccountDto creditAccount(long accountId, long amount) {
        Account savedAccount = accountRepository.findById(accountId).orElseThrow(()-> new NotFoundException("account not exist"));
        savedAccount.setBalance(savedAccount.getBalance() + amount);
        savedAccount =accountRepository.save(savedAccount);
        return objectMapper.convertValue(savedAccount , AccountDto.class);
    }
}
