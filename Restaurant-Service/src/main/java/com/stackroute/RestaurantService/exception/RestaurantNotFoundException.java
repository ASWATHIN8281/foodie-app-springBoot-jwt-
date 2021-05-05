package com.stackroute.RestaurantService.exception;
/**
 * To handle  RestaurantNotFoundException
 */
public class RestaurantNotFoundException extends Exception{
    private  String msg;
    public  RestaurantNotFoundException(){

    }
    public  RestaurantNotFoundException(String msg,String message){
        super(message);
        this.msg=msg;
    }


}
