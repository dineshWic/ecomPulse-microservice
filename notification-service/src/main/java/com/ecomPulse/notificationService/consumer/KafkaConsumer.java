package com.ecomPulse.notificationService.consumer;

import com.ecomPulse.notificationService.dto.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleNotification(OrderResponse orderResponse){
        log.info("Notification received for order : {}",orderResponse);
    }
}
