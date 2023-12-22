package com.cbi.blackFriday.service;

import com.cbi.blackFriday.entities.Product;

public interface IProductService {

    Product save (Product product);
    Product findById (Integer id);
    boolean validate (Product product);
}
