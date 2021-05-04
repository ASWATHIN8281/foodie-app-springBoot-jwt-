package com.stackroute.FavouriteService.Exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Value(value = "${data.exception.message4}")
    private String message4;
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<String>UserNameNotFound(UsernameNotFoundException e) {
        return new ResponseEntity<String>(message4, HttpStatus.FORBIDDEN);

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
