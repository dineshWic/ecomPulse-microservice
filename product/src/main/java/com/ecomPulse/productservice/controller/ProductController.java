package com.ecomPulse.productservice.controller;

import com.ecomPulse.productservice.dto.ProductRequest;
import com.ecomPulse.productservice.dto.ProductResponse;
import com.ecomPulse.productservice.service.ProductService;
import com.ecomPulse.productservice.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Create a new product
     * @param productRequest
     */
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Validated ProductRequest productRequest) {
        ProductResponse response =  productService.createProduct(productRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * find all products from the database
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> response =  productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    /**
     * Update product by id
     * @param productRequest
     */
    @PutMapping
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody @Validated ProductRequest productRequest, @RequestParam String id ){
        ProductResponse response =  productService.updateProduct(productRequest, id);
        return ResponseEntity.ok(response);
    }


}
