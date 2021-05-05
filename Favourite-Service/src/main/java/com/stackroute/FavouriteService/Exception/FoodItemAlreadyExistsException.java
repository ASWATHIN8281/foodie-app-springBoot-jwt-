package com.stackroute.FavouriteService.Exception;

/**
 * Exception handling method if the error due to fooditem already exists occur
 */
public class FoodItemAlreadyExistsException  extends Exception{
    private String msg;

    public FoodItemAlreadyExistsException(){

    }
    public FoodItemAlreadyExistsException(String msg,String message){
        super(message);
        this.msg=msg;
    }

}
