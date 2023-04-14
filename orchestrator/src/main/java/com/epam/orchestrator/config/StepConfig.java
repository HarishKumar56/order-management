package com.epam.orchestrator.config;

import com.epam.library.dto.ProductDto;
import com.epam.orchestrator.step.CreditAccountStep;
import com.epam.orchestrator.step.DebitAccountStep;
import com.epam.orchestrator.step.InventoryStep;
import com.epam.orchestrator.step.OrderStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class StepConfig {

    @Bean
    @Scope("prototype")
    public OrderStep getInventoryUpdateStep(List<ProductDto> productDtoList){
        return new InventoryStep(productDtoList);
    }

    @Bean
    @Scope("prototype")
    public OrderStep getCreditAccountStep(long userId , long amount){
        return new CreditAccountStep(userId , amount);
    }


    @Bean
    @Scope("prototype")
    public OrderStep getDebitAccountStep(long userId , long amount){
        return new DebitAccountStep(userId , amount);
    }




}
