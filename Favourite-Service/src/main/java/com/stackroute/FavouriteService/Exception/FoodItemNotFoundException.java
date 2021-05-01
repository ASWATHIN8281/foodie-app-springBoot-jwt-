package com.stackroute.FavouriteService.Exception;

public class FoodItemNotFoundException extends Exception{
    private String msg;
    public FoodItemNotFoundException(){

    }
    public FoodItemNotFoundException(String msg,String message){
        super(message);
        this.msg=msg;
    }
}
