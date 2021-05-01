package com.stackroute.RestaurantService.exception;

public class OrderAlreadyPlacedByCustomerException extends Exception{
    private String msg;

    public OrderAlreadyPlacedByCustomerException() {
    }

    public OrderAlreadyPlacedByCustomerException(String msg, String message) {
        super(message);
        this.msg = msg;
    }
}
