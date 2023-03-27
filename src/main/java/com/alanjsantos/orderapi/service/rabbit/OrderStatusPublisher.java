package com.alanjsantos.orderapi.service.rabbit;

import com.alanjsantos.orderapi.config.RabbitMQConfig;
import com.alanjsantos.orderapi.models.entity.Orders;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    public void publishOrderStatusUpdate(Orders order) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), RabbitMQConfig.ROUTING_KEY, order);
    }
}
