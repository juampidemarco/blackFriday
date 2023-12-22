package com.cbi.blackFriday.controller;

import com.cbi.blackFriday.dao.request.ProductRequest;
import com.cbi.blackFriday.entities.Product;
import com.cbi.blackFriday.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private IProductService service;

    @PostMapping("/product")
    public ResponseEntity<Product> product(@RequestBody ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDiscount(request.getDiscount());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        service.validate(product);

        return ResponseEntity.ok(service.save(product));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
