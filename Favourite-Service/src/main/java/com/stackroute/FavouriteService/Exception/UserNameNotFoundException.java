package com.stackroute.FavouriteService.Exception;

public class UserNameNotFoundException extends Exception{
    private String msg;

    public UserNameNotFoundException() {
    }
    public UserNameNotFoundException(String msg,String message){
        super(message);
        this.msg=msg;
    }
}
