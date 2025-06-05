package com.bullzen.order.controller;

import com.bullzen.order.dto.ApiResponse;
import com.bullzen.order.dto.OrderRequestDto;
import com.bullzen.order.dto.OrderResponseDto;
import com.bullzen.order.entities.Order;
import com.bullzen.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDto>> placeOrder(@Valid @RequestBody OrderRequestDto dto) {

        Order newOrder = orderService.saveOrder(dto);

        ApiResponse<OrderResponseDto> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Order placed successfully",
                new OrderResponseDto(newOrder)

        );

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.findOrderById(orderId);

        ApiResponse<OrderResponseDto> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Order fetched successfully",
                new OrderResponseDto(order)
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
