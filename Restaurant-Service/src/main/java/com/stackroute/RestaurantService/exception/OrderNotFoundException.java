package com.stackroute.RestaurantService.exception;

public class OrderNotFoundException extends Exception{
    private String msg;

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String msg, String message) {
        super(message);
        this.msg = msg;
    }
}
