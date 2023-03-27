package com.alanjsantos.orderapi.service.rabbit;

import com.alanjsantos.orderapi.config.RabbitMQConfig;
import com.alanjsantos.orderapi.models.entity.Orders;
import com.alanjsantos.orderapi.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusConsumer {

    @Autowired
    private OrderRepository orderRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleMessage(Orders order) {
        orderRepository.save(order);
    }

}
