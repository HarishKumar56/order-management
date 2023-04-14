package com.epam.orchestrator.client;

import com.epam.library.dto.AccountDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service")
@LoadBalancerClient(name = "account-service")
public interface AccountClient {


    @PutMapping("/api/account-service/accounts/{accountId}/debit/{amount}")
    AccountDto debitAccount(@PathVariable long accountId , @PathVariable long amount);

    @PutMapping("/api/account-service/accounts/{accountId}/credit/{amount}")
    AccountDto creditAccount(@PathVariable long accountId , @PathVariable long amount);
}
