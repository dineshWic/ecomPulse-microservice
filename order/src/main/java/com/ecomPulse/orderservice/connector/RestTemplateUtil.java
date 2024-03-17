package com.ecomPulse.orderservice.connector;

import com.ecomPulse.orderservice.dto.ProductRequest;
import com.ecomPulse.orderservice.dto.ProductResponse;
import com.ecomPulse.orderservice.exception.RestTemplateException;
import com.ecomPulse.orderservice.util.LogMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestTemplateUtil {
    private final RestTemplate restTemplate;

    @Value("${app.product-service.base-url}")
    private String productBaseUrl;

    private ProductRequest productRequestBuilder(ProductResponse productResponse, int newQty){
        return ProductRequest.builder()
                .name(productResponse.getName())
                .description(productResponse.getDescription())
                .price(productResponse.getPrice())
                .qty(newQty)
                .build();
    }


    public ProductResponse makeGetRequest(String id){
        String url = String.format(productBaseUrl+"/%s",id);
        ProductResponse productResponse = restTemplate.getForObject(url, ProductResponse.class);
        log.info(LogMsg.OrderMessages.PRODUCT_RESPONSE,productResponse);
        return productResponse;
    }

    public void makePutRequest(ProductResponse productResponse, int newQty){
        ProductRequest productRequest = this.productRequestBuilder(productResponse, newQty);


        String urlUpdate = String.format(productBaseUrl+"/%s",productResponse.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductRequest> requestEntity = new HttpEntity<>(productRequest, headers);

        try {
            ResponseEntity<ProductResponse> responseEntity = restTemplate.exchange(
                    urlUpdate,
                    HttpMethod.PUT,
                    requestEntity,
                    ProductResponse.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                log.info(LogMsg.OrderMessages.PRODUCT_RESPONSE,responseEntity.getBody());
            }

        }
        catch (Exception e){
            log.error(LogMsg.OrderMessages.REST_TEMPLATE_EXCEPTION);
            throw new RestTemplateException(LogMsg.OrderMessages.REST_TEMPLATE_EXCEPTION);
        }

    }


}
