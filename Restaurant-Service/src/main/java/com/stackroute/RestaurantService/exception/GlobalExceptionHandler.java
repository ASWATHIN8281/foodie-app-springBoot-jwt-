package com.stackroute.RestaurantService.exception;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;
    @ExceptionHandler(value = RestaurantAlreadyExistsException.class)
    public ResponseEntity<String> restaurantAlreadyExists(RestaurantAlreadyExistsException exception)
    {
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);
    }
    @Value(value = "${data.exception.message2}")
    private String message2;
    @ExceptionHandler(value = RestaurantNotFoundException.class)
    public ResponseEntity<String> restaurantNotFound(RestaurantNotFoundException exception)
    {
        return new ResponseEntity<String>(message2, HttpStatus.NOT_FOUND);
    }
    @Value(value = "${data.exception.message3}")
    private String message3;
    @ExceptionHandler(value = MenuItemAlreadyExistsException.class)
    public ResponseEntity<String> menuItemAlreadyExist(MenuItemAlreadyExistsException exception)
    {
        return new ResponseEntity<String>(message3, HttpStatus.CONFLICT);
    }
    @Value(value = "${data.exception.message4}")
    private String message4;
    @ExceptionHandler(value = MenuItemNotFoundException.class)
    public ResponseEntity<String> menuItemNotFound(MenuItemNotFoundException exception)
    {
        return new ResponseEntity<String>(message4, HttpStatus.NOT_FOUND);
    }
    @Value(value = "${data.exception.message5}")
    private String message5;
    @ExceptionHandler(value = OrderNotFoundException.class)
    public ResponseEntity<String> orderNotFound(OrderNotFoundException exception)
    {
        return new ResponseEntity<String>(message5, HttpStatus.NOT_FOUND);
    }
    @Value(value = "${data.exception.message6}")
    private String message6;
    @ExceptionHandler(value = OrderAlreadyPlacedByCustomerException.class)
    public ResponseEntity<String> orderAlreadyPlaced(OrderAlreadyPlacedByCustomerException exception)
    {
        return new ResponseEntity<String>(message6, HttpStatus.ALREADY_REPORTED);
    }
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }


}
