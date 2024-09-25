package com.assignment.inventory.service;

import com.assignment.inventory.entity.Product;
import com.assignment.inventory.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public String addProduct(Product product){
        log.info("addProduct service method started");
        productRepository.save(product);
        log.info("addProduct service method completed");
        return "Product is added to the DB";
    }
}
