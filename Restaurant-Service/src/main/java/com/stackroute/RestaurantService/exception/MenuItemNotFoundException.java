package com.stackroute.RestaurantService.exception;
/**
 * To handle  MenuItemNotFoundException
 */
public class MenuItemNotFoundException extends Exception{
    private  String msg;

    public MenuItemNotFoundException() {
    }

    public MenuItemNotFoundException(String msg, String message) {
        super(message);
        this.msg = msg;
    }
}
