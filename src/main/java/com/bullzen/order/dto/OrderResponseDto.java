package com.bullzen.order.dto;

import com.bullzen.order.entities.Order;
import com.bullzen.order.entities.OrderType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponseDto {

    private Long id;

    private Long userId;

    private Long stockId;

    private OrderType orderType;

    private Double bidPrice;

    private Long quantity;

    private LocalDateTime createdAt;

    private LocalDateTime executedAt;

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.userId = order.getUserId();
        this.stockId = order.getStockId();
        this.orderType = order.getOrderType();
        this.bidPrice = order.getBidPrice();
        this.quantity = order.getQuantity();
        this.createdAt = order.getCreatedAt();

        if(order.getExecutedAt() != null) {
            this.executedAt = order.getExecutedAt();
        }
    }
}
