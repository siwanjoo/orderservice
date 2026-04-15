package com.example.orderservice.order.dto;

import com.example.orderservice.order.domain.Order;
import com.example.orderservice.order.domain.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        OrderStatus orderStatus,
        LocalDateTime orderedAt,
        BigDecimal totalPrice,
        List<OrderItemResponse> items
) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderStatus(),
                order.getOrderedAt(),
                order.getTotalPrice(),
                order.getOrderItems().stream()
                        .map(OrderItemResponse::from)
                        .toList()
        );
    }
}
