package com.cbi.blackFriday.repository;


import com.cbi.blackFriday.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
