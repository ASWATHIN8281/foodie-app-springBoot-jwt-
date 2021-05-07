package com.stackroute.FavouriteService.Exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ControllerAdvice annotation will handle exceptions across
 * the whole application in one global handling component
 */
@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;

    /**
     *  @ExceptionHandler is an annotation used to
     *  handle the specific exceptions and sending the custom responses to the client.
     *  Exception handling for Fooditem already exist
     */
    @ExceptionHandler(FoodItemAlreadyExistsException.class)
    public ResponseEntity<String> foodItemAlreadyExists(FoodItemAlreadyExistsException exception) {
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);

    }

    @Value(value = "${data.exception.message2}")
    private String message2;

    /**
     *Exception handling for fooditem not found
     */
    @ExceptionHandler(FoodItemNotFoundException.class)
    public ResponseEntity<String> foodItemNotFound(FoodItemNotFoundException e) {
        return new ResponseEntity<String>(message2, HttpStatus.OK);

    }

    @Value(value = "${data.exception.message3}")
    private String message3;

    /**
     *Exception handling In case of unauthorised accessing
     */
    @ExceptionHandler(value = UnAuthorizedAccesException.class)
    public ResponseEntity<String> unAuthorizedAccess(UnAuthorizedAccesException e) {
        return new ResponseEntity<String>(message3, HttpStatus.UNAUTHORIZED);

    }
    /**
     *Exception handling In case of Favourite list not found accessing
     */
    @Value(value = "${data.exception.message5}")
    private  String message5;
    @ExceptionHandler(value = FavouriteListNotFoundException.class)
    public ResponseEntity<String> favouriteListNotFoundExceptio(FavouriteListNotFoundException e) {
        return new ResponseEntity<String>(message5, HttpStatus.UNAUTHORIZED);

    }
    @Value(value = "${data.exception.message4}")
    private String message4;
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<String>UserNameNotFound(UsernameNotFoundException e) {
        return new ResponseEntity<String>(message4, HttpStatus.FORBIDDEN);

    }

    /**
     *Method for Validation
     */

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//                                                                  HttpHeaders headers,
//                                                                  HttpStatus status, WebRequest request) {
//
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", new Date());
//        body.put("status", status.value());
//
//        //Get all errors
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(x -> x.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        body.put("errors", errors);
//
//        return new ResponseEntity<>(body, headers, status);
//
//    }


}
