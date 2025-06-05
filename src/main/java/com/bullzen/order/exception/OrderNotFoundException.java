package com.bullzen.order.exception;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException (String message) {
        super(message);
    }
}
