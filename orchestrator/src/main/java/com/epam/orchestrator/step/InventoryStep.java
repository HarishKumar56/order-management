package com.epam.orchestrator.step;

import com.epam.library.dto.ProductDto;
import com.epam.orchestrator.client.InventoryClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InventoryStep implements OrderStep{

    private final List<ProductDto> productDtoList;

    @Autowired
    private InventoryClient inventoryClient;

    private boolean status ;

    public InventoryStep(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
        status = false;
    }


    @Override
    public void process() {
        inventoryClient.debitInventory(productDtoList);
        status= true;
    }

    @Override
    public void handleFailure() {
        inventoryClient.creditInventory(productDtoList);
    }


    @Override
    public boolean getStatus() {
        return status;
    }
}
