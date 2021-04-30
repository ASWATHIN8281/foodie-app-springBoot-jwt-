package com.stackroute.UserService.controller;

import com.stackroute.UserService.model.User;
import com.stackroute.UserService.model.UserDto;
import com.stackroute.UserService.service.JwtUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class UserController {
    @Autowired
    private JwtUserDetailsService service;

    @PutMapping("user")
    public ResponseEntity<User> updateUserDetails(@RequestBody UserDto userDto){
        return new ResponseEntity<>(service.updateUser(userDto), HttpStatus.OK);
    }
    @DeleteMapping("user/{id}")
    public ResponseEntity<User> deleteUserDetails(@PathVariable int id){
        return new ResponseEntity<>(service.deleteUser(id),HttpStatus.OK);
    }
    @GetMapping("user/{id}")
    public ResponseEntity<User> displayUserDetails(@PathVariable int id){
        return new ResponseEntity<>(service.getUserById(id),HttpStatus.OK);
    }
}
