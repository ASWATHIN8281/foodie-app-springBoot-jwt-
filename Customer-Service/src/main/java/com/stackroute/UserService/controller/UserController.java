package com.stackroute.UserService.controller;

import com.stackroute.UserService.exception.CustomerUnknownException;
import com.stackroute.UserService.model.User;
import com.stackroute.UserService.model.UserDto;
import com.stackroute.UserService.service.JwtUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class UserController {
    @Autowired
    private JwtUserDetailsService service;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    /* updating customer details */

    @PutMapping("/customer")
    public ResponseEntity<User> updateUserDetails(@Valid @RequestBody User user,@RequestParam (value = "id") int id) throws CustomerUnknownException {
        logger.info("Customer account updated");
        return new ResponseEntity<>(service.updateUser(user,id), HttpStatus.CREATED);
    }

    /* remove customer account */

    @DeleteMapping("/customer/remove/{id}")
    public ResponseEntity<User> deleteUserDetails(@PathVariable int id) throws CustomerUnknownException {
        logger.info("Customer details deleted");
        return new ResponseEntity<>(service.deleteUser(id),HttpStatus.OK);
    }

    /* getting customer details by customer Id */

    @GetMapping("/customer/{id}")
    public ResponseEntity<User> displayUserDetails(@PathVariable int id) throws CustomerUnknownException {
        logger.info("Customer account fetched by id");
        return new ResponseEntity<>(service.getUserById(id),HttpStatus.FOUND);
    }
}
