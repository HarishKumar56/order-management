package com.epam.inventory.service;

import com.epam.inventory.entity.Inventory;
import com.epam.inventory.repo.InventoryRepository;
import com.epam.library.dto.ProductDto;
import com.epam.library.exception.InsufficientException;
import com.epam.library.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public ProductDto addInventory(ProductDto productDto) {
        Inventory inventory = inventoryRepository.findById(productDto.getProductId()).orElse(new Inventory());
        inventory.setProductId(productDto.getProductId());
        inventory.setQuantity(productDto.getQuantity());
        inventoryRepository.save(inventory);
        return objectMapper.convertValue(inventory , ProductDto.class);
    }

    @Override
    @Transactional
    public List<ProductDto> creditInventories(List<ProductDto> productDtos) {
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtos.forEach(productDto -> {
            Inventory inventory =  inventoryRepository.findById(productDto.getProductId()).orElseThrow(()-> new NotFoundException("Inventory not found"));;
            inventory.setQuantity(inventory.getQuantity()+productDto.getQuantity());
            inventoryRepository.save(inventory);
            productDtoList.add(objectMapper.convertValue(inventory,ProductDto.class));
        });
        return productDtoList;
    }

    @Override
    @Transactional
    public List<ProductDto> debitInventories(List<ProductDto> productDtos) {
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtos.forEach(productDto -> {
            Inventory inventory =  inventoryRepository.findById(productDto.getProductId()).orElseThrow(()-> new NotFoundException("Inventory not found"));;
            if(inventory.getQuantity()- productDto.getQuantity() <0){
                throw new InsufficientException("insufficient inventory to perform this action");
            }
            inventory.setQuantity(inventory.getQuantity()-productDto.getQuantity());
            inventoryRepository.save(inventory);
            productDtoList.add(objectMapper.convertValue(inventory,ProductDto.class));
        });
        return productDtoList;
    }

    @Override
    public ProductDto getInventoriesById(long productId) {
        Inventory inventory =  inventoryRepository.findById(productId).orElseThrow(()-> new NotFoundException("Inventory not found"));
        return objectMapper.convertValue(inventory , ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllInventories() {
        return inventoryRepository.findAll().stream().map(inventory -> objectMapper.convertValue(inventory , ProductDto.class)).collect(Collectors.toList());

    }
}
