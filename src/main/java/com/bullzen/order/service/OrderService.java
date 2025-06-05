package com.bullzen.order.service;

import com.bullzen.order.dto.OrderRequestDto;
import com.bullzen.order.entities.Order;
import com.bullzen.order.exception.OrderNotFoundException;
import com.bullzen.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order saveOrder(OrderRequestDto dto) {

        Order newOrder = Order.builder()
                .userId(dto.getUserId())
                .stockId(dto.getStockId())
                .orderType(dto.getOrderType())
                .bidPrice(dto.getBidPrice())
                .quantity(dto.getQuantity())
                .createdAt(LocalDateTime.now())
                .build();

        return orderRepository.save(newOrder);
    }

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with order Id: " + orderId));
    }
}
