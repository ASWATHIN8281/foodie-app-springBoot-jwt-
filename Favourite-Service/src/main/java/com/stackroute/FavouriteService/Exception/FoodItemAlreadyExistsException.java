package com.stackroute.FavouriteService.Exception;

public class FoodItemAlreadyExistsException  extends Exception{
    private String msg;
    public FoodItemAlreadyExistsException(){

    }
    public FoodItemAlreadyExistsException(String msg,String message){
        super(message);
        this.msg=msg;
    }

}
