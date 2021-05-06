package com.stackroute.FavouriteService.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FavouriteListNotFoundException extends Exception{
    private String msg;

    public FavouriteListNotFoundException(String message, String msg) {
        super(message);
        this.msg = msg;
    }
}
