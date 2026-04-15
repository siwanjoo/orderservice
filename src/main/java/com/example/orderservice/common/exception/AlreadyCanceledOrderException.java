package com.example.orderservice.common.exception;

public class AlreadyCanceledOrderException extends BusinessException {

    public AlreadyCanceledOrderException() {
        super(ErrorCode.ALREADY_CANCELED_ORDER);
    }
}
