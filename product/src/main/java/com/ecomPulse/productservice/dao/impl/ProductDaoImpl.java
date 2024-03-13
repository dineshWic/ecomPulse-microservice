package com.ecomPulse.productservice.dao.impl;

import com.ecomPulse.productservice.Util.LogMsg;
import com.ecomPulse.productservice.dao.ProductDao;
import com.ecomPulse.productservice.model.Product;
import com.ecomPulse.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;



@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    public Product save(Product product){
        log.info(LogMsg.ProductMessages.PRODUCT_CREATE,product);
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        log.info(LogMsg.ProductMessages.FIND_ALL_PRODUCT);
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(String id){
        log.info(LogMsg.ProductMessages.PRODUCT_BY_ID,id);
        return productRepository.findById(id);
    }


}
