package com.ecomPulse.productservice.service.impl;

import com.ecomPulse.productservice.Util.LogMsg;
import com.ecomPulse.productservice.converter.ProductConverter;
import com.ecomPulse.productservice.dao.ProductDao;
import com.ecomPulse.productservice.dto.ProductRequest;
import com.ecomPulse.productservice.dto.ProductResponse;
import com.ecomPulse.productservice.exception.NotFoundException;
import com.ecomPulse.productservice.exception.ServiceException;
import com.ecomPulse.productservice.model.Product;
import com.ecomPulse.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    private final ProductConverter productConverter;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productConverter.productRequestToProductConverter(productRequest);

        try{
            Product savedProduct = productDao.save(product);
            ProductResponse response = productConverter.productToProductResponseConverter(savedProduct);
            log.info(LogMsg.ProductMessages.PRODUCT_CREATE, savedProduct);
            return response;
        }
        catch (Exception e){
            log.error(LogMsg.ProductMessages.PRODUCT_CREATE_ERROR);
            throw new ServiceException(LogMsg.ProductMessages.PRODUCT_CREATE_ERROR);
        }

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        try{
            List<Product> products = productDao.findAll();
            return productConverter.productListToProductResponseListConverter(products);
        }
        catch (Exception e){
            log.error(LogMsg.ProductMessages.PRODUCT_RETRIVE_ERROR);
            throw new ServiceException(LogMsg.ProductMessages.PRODUCT_RETRIVE_ERROR);
        }

    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, String id) {
        Product retrievedProduct = this.findById(id);

        retrievedProduct.setDescription(productRequest.getDescription());
        retrievedProduct.setName(productRequest.getName());
        retrievedProduct.setPrice(productRequest.getPrice());

        try{
            Product updatedProduct = productDao.save(retrievedProduct);
            ProductResponse response = productConverter.productToProductResponseConverter(updatedProduct);
            log.info(LogMsg.ProductMessages.PRODUCT_UPDATED, updatedProduct);
            return response;
        }
        catch (Exception e){
            log.error(LogMsg.ProductMessages.PRODUCT_UPDATE_ERROR);
            throw new ServiceException(LogMsg.ProductMessages.PRODUCT_UPDATE_ERROR);
        }
    }

    @Override
    public Product findById(String id){
        try{
            Optional<Product> retrievedProduct = productDao.findById(id);

            if (retrievedProduct.isEmpty()){
                log.error(LogMsg.ProductMessages.PRODUCT_NOT_FOUND_ERROR);
                throw new NotFoundException(LogMsg.ProductMessages.PRODUCT_NOT_FOUND_ERROR);
            }
            return retrievedProduct.get();
        }
        catch (NotFoundException e){
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        catch (Exception e){
            log.error(LogMsg.ProductMessages.PRODUCT_RETRIVE_BY_ID_ERROR);
            throw new ServiceException(LogMsg.ProductMessages.PRODUCT_RETRIVE_BY_ID_ERROR);
        }
    }

    @Override
    public ProductResponse findByProductId(String id){

        Product retrievedProduct = this.findById(id);

        ProductResponse response = productConverter.productToProductResponseConverter(retrievedProduct);
        log.info(LogMsg.ProductMessages.PRODUCT_BY_ID, retrievedProduct);
        return response;


    }

}
