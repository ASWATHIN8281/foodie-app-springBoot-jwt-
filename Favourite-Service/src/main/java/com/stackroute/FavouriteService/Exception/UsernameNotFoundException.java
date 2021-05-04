package com.stackroute.FavouriteService.Exception;

public class UsernameNotFoundException extends Exception{
    private String msg;

    public UsernameNotFoundException() {
    }
    public UsernameNotFoundException(String msg, String message){
        super(message);
        this.msg=msg;
    }
}
