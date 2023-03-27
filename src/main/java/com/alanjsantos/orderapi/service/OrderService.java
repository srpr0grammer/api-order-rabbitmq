package com.alanjsantos.orderapi.service;

import com.alanjsantos.orderapi.models.dto.CreateOrderDTO;
import com.alanjsantos.orderapi.models.entity.Orders;
import com.alanjsantos.orderapi.models.enums.OrderStatus;
import com.alanjsantos.orderapi.repository.OrderRepository;
import com.alanjsantos.orderapi.repository.ProductRepository;
import com.alanjsantos.orderapi.service.exception.ObjectNotFoundException;
import com.alanjsantos.orderapi.service.rabbit.OrderStatusPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;



    @Autowired
    private OrderStatusPublisher orderStatusPublisher;

    public Orders create(CreateOrderDTO dto) {
        var order = new Orders();
        order.setStatusOrder(OrderStatus.PROCESSING);

        var products = productRepository.findByIdIn(dto.getProductIds());
        if (products.isEmpty()) {
            throw new ObjectNotFoundException("Product not found.");
        }
        order.setProducts(products);
        order.setDateOrder(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public Orders updateStatus(OrderStatus newStatus, Long orderId) {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            var order = optionalOrder.get();
            order.setStatusOrder(newStatus);
            var updateStatus = orderRepository.save(order);

            orderStatusPublisher.publishOrderStatusUpdate(updateStatus);

            return updateStatus;
        } else {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
    }

    public Orders findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Order not found."));
    }

    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    public void delete(Long id) {
        var order = findById(id);
        orderRepository.deleteById(order.getId());
    }
}
