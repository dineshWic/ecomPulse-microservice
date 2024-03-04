package com.ecomPulse.productservice.dao;

import com.ecomPulse.productservice.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(String id);
}
