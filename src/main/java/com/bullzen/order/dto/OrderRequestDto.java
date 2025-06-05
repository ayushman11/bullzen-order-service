package com.bullzen.order.dto;

import com.bullzen.order.entities.OrderType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDto {

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotNull(message = "Stock Id is required")
    private Long stockId;

    @NotNull(message = "Order Type is required: BUY/SELL")
    private OrderType orderType;

    private Double bidPrice;

    @NotNull(message = "Quantity is required")
    private Long quantity;
}
