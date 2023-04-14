package com.epam.orchestrator.step;

import com.epam.orchestrator.client.AccountClient;
import org.springframework.beans.factory.annotation.Autowired;

public class DebitAccountStep implements OrderStep{

    private long userId;
    private long amount;

    @Autowired
    private AccountClient accountClient;

    public DebitAccountStep(long userId, long amount) {
        this.userId = userId;
        this.amount = amount;
    }

    private boolean status = false;
    @Override
    public void process() {
        accountClient.debitAccount(userId, amount);
        status = true;
    }

    @Override
    public void handleFailure() {
        accountClient.creditAccount(userId, amount);
    }


    @Override
    public boolean getStatus() {
        return this.status;
    }
}
