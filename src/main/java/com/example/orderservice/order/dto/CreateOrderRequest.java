package com.example.orderservice.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateOrderRequest(
        @NotEmpty(message = "items is required")
        List<@Valid CreateOrderItemRequest> items
) {
}
