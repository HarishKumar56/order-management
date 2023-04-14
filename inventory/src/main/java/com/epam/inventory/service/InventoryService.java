package com.epam.inventory.service;

import com.epam.library.dto.ProductDto;

import java.util.List;

public interface InventoryService {
    ProductDto addInventory(ProductDto productDto);


    List<ProductDto> creditInventories(List<ProductDto> productDto);
    List<ProductDto> debitInventories(List<ProductDto> productDto);

    ProductDto getInventoriesById(long productId);

    List<ProductDto> getAllInventories();

}
