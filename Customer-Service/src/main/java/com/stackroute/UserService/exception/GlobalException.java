package com.stackroute.UserService.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalException {
    @Value(value = "${data.exception.message1}")
    private String message1;
    @Value(value = "${data.exception.message2}")
    private String message2;

    /*
    * exception handling for
    * customer already exists
    * */

    @ExceptionHandler(value = CustomerAlreadyExistsException.class)
    public ResponseEntity<String> customerAlreadyExists(CustomerAlreadyExistsException exception){
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);
    }

    /*
     * exception handling for
     * customer unknown scenario
     * */

    @ExceptionHandler(value = CustomerUnknownException.class)
    public ResponseEntity<String> customerUnknownExists(CustomerUnknownException exception){
        return new ResponseEntity<String>(message2, HttpStatus.CONFLICT);
    }

    /*
     * handling
     * method argument not valid exception
     * */

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
