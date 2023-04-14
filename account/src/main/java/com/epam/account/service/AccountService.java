package com.epam.account.service;


import com.epam.library.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(long accountId);

    AccountDto debitAccount(long accountId, long amount);

    AccountDto creditAccount(long accountId, long amount);
}
