package com.ecomPulse.productservice.service;

import com.ecomPulse.productservice.dto.ProductRequest;
import com.ecomPulse.productservice.dto.ProductResponse;
import com.ecomPulse.productservice.model.Product;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(ProductRequest productRequest, String id);

    Product findById(String id);

    ProductResponse findByProductId(String id);
}
