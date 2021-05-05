package com.stackroute.FavouriteService.Exception;
/**
 * Exception handling method if the error due to fooditem not found occur
 */
public class FoodItemNotFoundException extends Exception{
    private String msg;
    public FoodItemNotFoundException(){

    }
    public FoodItemNotFoundException(String msg,String message){
        super(message);
        this.msg=msg;
    }
}
