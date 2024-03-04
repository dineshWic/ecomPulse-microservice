package com.ecomPulse.productservice.converter;

import com.ecomPulse.productservice.dto.ProductRequest;
import com.ecomPulse.productservice.dto.ProductResponse;
import com.ecomPulse.productservice.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConverter {

    public Product productRequestToProductConverter(ProductRequest request){
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    public ProductResponse productToProductResponseConverter(Product request){
        return ProductResponse.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    public List<ProductResponse> productListToProductResponseListConverter(List<Product> request){
        return request.stream().map(this::productToProductResponseConverter).toList();
    }

}
