package com.ecomPulse.productservice.controller;

import com.ecomPulse.productservice.dto.OrderQuentity;
import com.ecomPulse.productservice.dto.ProductRequest;
import com.ecomPulse.productservice.dto.ProductResponse;
import com.ecomPulse.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> response =  productService.getAllProducts();
        return ResponseEntity.ok(response);
    }

    /**
     * get a product by id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
        ProductResponse response =  productService.findByProductId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/quantity")
    public ResponseEntity<List<ProductResponse>> getProductQuantities(@RequestParam List<String> ids) {
        log.info("This is ids: {} ",ids);
        List<ProductResponse> response =  productService.findQuantityByProductId(ids);
        return ResponseEntity.ok(response);
    }


    /**
     * Update product by id
     * @param productRequest
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody @Validated ProductRequest productRequest, @PathVariable String id ){
        ProductResponse response =  productService.updateProduct(productRequest, id);
        return ResponseEntity.ok(response);
    }


}
