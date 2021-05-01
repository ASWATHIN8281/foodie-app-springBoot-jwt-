package com.stackroute.RestaurantService.exception;

public class MenuItemAlreadyExistsException extends Exception{
    private String msg;
    public MenuItemAlreadyExistsException() {
    }

    public MenuItemAlreadyExistsException(String msg,String message) {
        super(message);
        this.msg=msg;
    }
}
