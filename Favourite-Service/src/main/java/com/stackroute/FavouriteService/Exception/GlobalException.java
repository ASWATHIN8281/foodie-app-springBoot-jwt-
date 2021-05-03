package com.stackroute.FavouriteService.Exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;

    @ExceptionHandler(FoodItemAlreadyExistsException.class)
    public ResponseEntity<String> foodItemAlreadyExists(FoodItemAlreadyExistsException exception) {
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);

    }

    @Value(value = "${data.exception.message2}")
    private String message2;

    @ExceptionHandler(FoodItemNotFoundException.class)
    public ResponseEntity<String> foodItemNotFound(FoodItemNotFoundException e) {
        return new ResponseEntity<String>(message2, HttpStatus.OK);

    }

    @Value(value = "${data.exception.message3}")
    private String message3;
    @ExceptionHandler(value = UnAuthorizedAccesException.class)
    public ResponseEntity<String> unAuthorizedAccess(UnAuthorizedAccesException e) {
        return new ResponseEntity<String>(message3, HttpStatus.UNAUTHORIZED);

    }
}
