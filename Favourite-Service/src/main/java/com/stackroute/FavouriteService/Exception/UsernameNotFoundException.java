package com.stackroute.FavouriteService.Exception;
/**
 * Exception handling method if the error due to Username not found in db occur
 */
public class UsernameNotFoundException extends Exception{
    private String msg;

    public UsernameNotFoundException() {
    }
    public UsernameNotFoundException(String msg, String message){
        super(message);
        this.msg=msg;
    }
}
