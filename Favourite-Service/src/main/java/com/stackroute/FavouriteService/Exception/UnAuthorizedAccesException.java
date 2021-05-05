package com.stackroute.FavouriteService.Exception;

import lombok.NoArgsConstructor;
/**
 * Exception handling method if the error due to UnAuthorizedAccess  occur
 */
@NoArgsConstructor
public class UnAuthorizedAccesException extends Exception{

    private String msg;

    public UnAuthorizedAccesException(String message, String msg) {
        super(message);
        this.msg = msg;
    }
}
