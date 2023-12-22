package com.cbi.blackFriday.service.impl;

import com.cbi.blackFriday.entities.Product;
import com.cbi.blackFriday.exception.*;
import com.cbi.blackFriday.repository.ProductRepository;
import com.cbi.blackFriday.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductServiceImpl implements IProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository repository;
    @Override
    public Product save(Product product) {
        log.debug("enter save product service");
        Product savedProduct;

        try {
            savedProduct = repository.save(product);
        } catch (Exception e){
            log.error("error saving product: "+ e.getMessage());
            throw new ProductException("error save user");
        }

        return savedProduct;
    }

    @Override
    public Product findById(Integer id) {
        log.debug("enter findById product service");

        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("product not found"));
    }

    @Override
    public boolean validate(Product product) {
        log.debug("enter validate product service");

        if (Objects.isNull(product.getPrice())) {
            log.error("error validating product. Price is required");
            throw new ApiRequestException("Price is required");
        }
        if (Objects.isNull(product.getStock())) {
            log.error("error validating product. Stock is required");
            throw new ApiRequestException("Stock is required");
        }
        if (Objects.isNull(product.getName())) {
            log.error("error validating product. Name is required");
            throw new ApiRequestException("Name is required");
        }
        return true;
    }
}
