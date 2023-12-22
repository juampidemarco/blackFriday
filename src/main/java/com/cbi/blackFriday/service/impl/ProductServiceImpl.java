package com.cbi.blackFriday.service.impl;


import com.cbi.blackFriday.entities.Product;
import com.cbi.blackFriday.exception.ProductNotFoundException;
import com.cbi.blackFriday.repository.ProductRepository;
import com.cbi.blackFriday.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository repository;
    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product findById(Integer id) {
        log.debug("enter findById product service");

        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException("product not found"));
    }
}
