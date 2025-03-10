package com.market.order;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Value("${message.queue.product}")
    private String productQueue;

    @Value("${message.queue.payment}")
    private String paymentQueue;

    private final RabbitTemplate rabbitTemplate;
    // rabbitMQ로 요청을 보낼 때 사용하는 template

    public void createOrder(String orderId) {
        rabbitTemplate.convertAndSend(productQueue, orderId);
        rabbitTemplate.convertAndSend(paymentQueue, orderId);
    }
}