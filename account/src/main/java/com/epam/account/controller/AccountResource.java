package com.epam.account.controller;


import com.epam.account.service.AccountService;
import com.epam.library.dto.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/account-service/")
@RestController
@Slf4j
public class AccountResource {

    @Autowired
    private AccountService accountService;

    @PostMapping("accounts")
    public AccountDto addAccount(@RequestBody AccountDto accountDto){
        return accountService.createAccount(accountDto);
    }

    @GetMapping("accounts/{accountId}")
    public AccountDto getAccount(@PathVariable long accountId){
        log.info("getting the account details");
        return accountService.getAccountById(accountId);
    }

    @PutMapping("accounts/{accountId}/debit/{amount}")
    public AccountDto debitAccount(@PathVariable long accountId , @PathVariable long amount) throws InterruptedException {
        log.info("debiting the account");
        //Thread.sleep(2000);

        return accountService.debitAccount(accountId , amount);
    }

    @PutMapping("accounts/{accountId}/credit/{amount}")
    public AccountDto creditAccount(@PathVariable long accountId , @PathVariable long amount) throws InterruptedException {
        log.info("crediting the account");

        return accountService.creditAccount(accountId , amount);
    }


}
