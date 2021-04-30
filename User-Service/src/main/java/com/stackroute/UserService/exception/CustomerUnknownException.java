package com.stackroute.UserService.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerUnknownException extends Exception{
    private String msg;

    public CustomerUnknownException(String message, String msg) {
        super(message);
        this.msg = msg;
    }
}
