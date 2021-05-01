package com.stackroute.UserService.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @Value(value = "${data.exception.message1}")
    private String message1;
    @Value(value = "${data.exception.message2}")
    private String message2;

    @ExceptionHandler(value = CustomerAlreadyExistsException.class)
    public ResponseEntity<String> customerAlreadyExists(CustomerAlreadyExistsException exception){
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = CustomerUnknownException.class)
    public ResponseEntity<String> customerUnknownExists(CustomerUnknownException exception){
        return new ResponseEntity<String>(message2, HttpStatus.CONFLICT);
    }
}
