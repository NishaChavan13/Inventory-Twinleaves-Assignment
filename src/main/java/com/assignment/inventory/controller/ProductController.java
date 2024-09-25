package com.assignment.inventory.controller;

import com.assignment.inventory.entity.Product;
import com.assignment.inventory.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping("add")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        log.info("addProduct controller method started");
        String result = productService.addProduct(product);
        log.info("addProduct controller method completed");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
