package com.stackroute.FavouriteService.Exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @Value(value = "${data.exception.message1}")
    private String message1;

    @ExceptionHandler(value = FoodItemAlreadyExistsException.class)
    public ResponseEntity<String> foodItemAlreadyExists(FoodItemAlreadyExistsException exception) {
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);

    }

    @Value(value = "${data.exception.message2}")
    private String message2;

    @ExceptionHandler(value = FoodItemNotFoundException.class)
    public ResponseEntity<String> foodItemNotFound(FoodItemNotFoundException e) {
        return new ResponseEntity<String>(message2, HttpStatus.CONFLICT);

    }
}