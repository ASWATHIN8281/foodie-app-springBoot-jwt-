package com.stackroute.UserService.exception;

import lombok.NoArgsConstructor;

/*
 * exception handling for
 * customer already exists
 * */

@NoArgsConstructor
public class CustomerAlreadyExistsException extends Exception{
    private String msg;

    public CustomerAlreadyExistsException(String msg,String message) {
        super(message);
        this.msg=msg;
    }
}
