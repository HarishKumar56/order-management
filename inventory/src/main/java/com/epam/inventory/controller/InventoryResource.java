package com.epam.inventory.controller;


import com.epam.inventory.service.InventoryService;
import com.epam.library.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/inventory-service/")
@RestController
@Slf4j
public class InventoryResource {

    @Autowired
    private InventoryService inventoryService;


    @PostMapping("/inventories")
    public ProductDto addInventory(@RequestBody ProductDto productDto){
        log.info("adding inventories");
        return inventoryService.addInventory(productDto);
    }

    @PutMapping("/inventories/credit")
    public List<ProductDto> creditInventory(@RequestBody List<ProductDto> productDto){
        log.info("crediting inventories");
        return inventoryService.creditInventories(productDto);
    }

    @PutMapping("/inventories/debit")
    public List<ProductDto> debitInventory(@RequestBody List<ProductDto> productDto){
        log.info("debiting inventories");
        return inventoryService.debitInventories(productDto);
    }

    @GetMapping("/inventories")
    public List<ProductDto> getAllInventory(){
        log.info("getting all inventories");
        return inventoryService.getAllInventories();
    }

    @GetMapping("/inventories/{productId}")
    public ProductDto getByIdInventory(@PathVariable long productId){
        log.info("getting inventories by id");
        return inventoryService.getInventoriesById(productId);
    }
}
