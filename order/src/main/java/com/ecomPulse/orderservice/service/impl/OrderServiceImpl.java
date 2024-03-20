package com.ecomPulse.orderservice.service.impl;

import com.ecomPulse.orderservice.connector.RestTemplateUtil;
import com.ecomPulse.orderservice.converter.OrderConverter;
import com.ecomPulse.orderservice.dao.OrderDao;
import com.ecomPulse.orderservice.dto.OrderItemsDto;
import com.ecomPulse.orderservice.dto.OrderRequest;
import com.ecomPulse.orderservice.dto.OrderResponse;
import com.ecomPulse.orderservice.dto.ProductResponse;
import com.ecomPulse.orderservice.exception.ProductQuantityNotEnoughException;
import com.ecomPulse.orderservice.exception.RestTemplateException;
import com.ecomPulse.orderservice.exception.ServiceException;
import com.ecomPulse.orderservice.model.Order;
import com.ecomPulse.orderservice.service.OrderService;
import com.ecomPulse.orderservice.util.LogMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final RestTemplateUtil restTemplateUtil;
    private final OrderConverter orderConverter;
    private final KafkaTemplate<String, OrderResponse> kafkaTemplate;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = orderConverter.orderRequestToOrderConverter(orderRequest);

        //Check ordered product quantity is available or not
        try{
            //make separate requests to product service
            for (OrderItemsDto orderItemsDto : orderRequest.getOrderItemsDtoList() ) {

                String id = orderItemsDto.getProductId();
                ProductResponse productResponse = restTemplateUtil.makeGetRequest(id);

                //check available quantity from product response
                if (productResponse != null){
                    if (productResponse.getQty() < orderItemsDto.getQuantity()){
                        String errMsg = String.format("Product id: %s and product name: %s has only %s quantity",orderItemsDto.getProductId(),productResponse.getName(),productResponse.getQty());
                        log.error(LogMsg.OrderMessages.PRODUCT_OUT_OF_STOCK,orderItemsDto.getProductId(),productResponse.getName(),productResponse.getQty());
                        throw new ProductQuantityNotEnoughException(errMsg);
                    }
                    else {
                        //reduce the product quantity und update the product service table
                        int newQty = productResponse.getQty() - orderItemsDto.getQuantity();
                        restTemplateUtil.makePutRequest(productResponse,newQty);
                    }
                }
                else{
                    log.error(LogMsg.OrderMessages.PRODUCT_NOT_FOUND_ERROR);
                    throw new IllegalArgumentException(LogMsg.OrderMessages.PRODUCT_NOT_FOUND_ERROR);
                }
            }

            Order savedOrder =  orderDao.save(order);
            OrderResponse response = orderConverter.orderToOrderResponseConverter(savedOrder);
            log.info("Notification is sending...");
            kafkaTemplate.send("notificationTopic",response);
            log.info("Notification is sent...");
            return response;

        }
        catch (IllegalArgumentException e){
            log.error(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
        catch (RestTemplateException | ServiceException  e){
            log.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }


    }


}
