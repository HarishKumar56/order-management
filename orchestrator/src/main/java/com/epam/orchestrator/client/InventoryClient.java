package com.epam.orchestrator.client;

import com.epam.library.dto.ProductDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "inventory-service")
@LoadBalancerClient(name = "inventory-service")
public interface InventoryClient {


    @PutMapping("/api/inventory-service//inventories/credit")
    List<ProductDto> creditInventory(@RequestBody List<ProductDto> productDto);

    @PutMapping("/api/inventory-service//inventories/debit")
    List<ProductDto> debitInventory(@RequestBody List<ProductDto> productDto);

}
