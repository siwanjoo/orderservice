package com.example.orderservice.common.response;

public record ErrorResponse(
        String code,
        String message
) {
}
