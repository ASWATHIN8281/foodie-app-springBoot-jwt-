package com.stackroute.RestaurantService.exception;
/**
 * To handle  RestaurantAlreadyExistsException
 */
public class RestaurantAlreadyExistsException extends  Exception{
    private String msg;
    public RestaurantAlreadyExistsException(){

    }

    public RestaurantAlreadyExistsException(String msg, String message) {
        super(message);
        this.msg = msg;
    }
}
