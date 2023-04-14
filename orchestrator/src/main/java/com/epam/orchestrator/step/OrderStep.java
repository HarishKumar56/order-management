package com.epam.orchestrator.step;

public interface OrderStep {

    void process();
    void handleFailure();
    boolean getStatus();
}
