package com.stackroute.RestaurantService.exception;
/**
 * To handle  OrderNotFoundException
 */
public class OrderNotFoundException extends Exception{
    private String msg;

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String msg, String message) {
        super(message);
        this.msg = msg;
    }
}
