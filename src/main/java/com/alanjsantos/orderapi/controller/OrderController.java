package com.alanjsantos.orderapi.controller;

import com.alanjsantos.orderapi.config.RabbitMQConfig;
import com.alanjsantos.orderapi.models.dto.CreateOrderDTO;
import com.alanjsantos.orderapi.models.dto.OrderStatusUpDTO;
import com.alanjsantos.orderapi.models.dto.ResponseOrdersDTO;
import com.alanjsantos.orderapi.models.entity.Orders;
import com.alanjsantos.orderapi.models.enums.OrderStatus;
import com.alanjsantos.orderapi.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<ResponseOrdersDTO> create(@Valid @RequestBody CreateOrderDTO orderDTO) {
        var order = orderService.create(orderDTO);

        return ResponseEntity.ok().body(modelMapper.map(order, ResponseOrdersDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrdersDTO> findById(@PathVariable Long id) {
        var order = orderService.findById(id);
        var orderDTO = modelMapper.map(order, ResponseOrdersDTO.class);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponseOrdersDTO>> findAll() {
        var orders = orderService.findAll();
        var orderDTOs = orders.stream()
                .map(order -> modelMapper.map(order, ResponseOrdersDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("/{orderId}/status")
//    public String updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatusUpDTO message) {
//        message.setStatus(OrderStatus.COMPLETED);
//        message.setId(orderId);
//        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, message);
//
//        return "Message Publicada.";
//    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Orders> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatus newStatus) {
        try {
            var updatedOrder = orderService.updateStatus(newStatus, orderId);

            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}