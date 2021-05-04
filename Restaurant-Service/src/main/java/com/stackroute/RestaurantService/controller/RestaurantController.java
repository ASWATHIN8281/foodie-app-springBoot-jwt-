package com.stackroute.RestaurantService.controller;
import com.stackroute.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.stackroute.RestaurantService.exception.RestaurantNotFoundException;
import com.stackroute.RestaurantService.model.Order;
import com.stackroute.RestaurantService.model.Restaurant;
import com.stackroute.RestaurantService.service.RestaurantDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class RestaurantController {
    @Autowired
    private RestaurantDAO service;
    @PostMapping("restaurant")
    public ResponseEntity<Restaurant>saveRestaurant(@Valid @RequestBody Restaurant restaurant) throws RestaurantAlreadyExistsException {
        return new ResponseEntity<Restaurant>(service.addRestaurant(restaurant), HttpStatus.CREATED);
    }
    @GetMapping("restaurants")
    public ResponseEntity<List<Restaurant>> getAllOrders() {
        return new ResponseEntity<List<Restaurant>>((List<Restaurant>) service.getAllRestaurants(), HttpStatus.OK);
    }
    @DeleteMapping("restaurant/{id}")
    public ResponseEntity<Restaurant>getRestaurantAfterDeleting(@PathVariable("id")int id)throws RestaurantNotFoundException {
        return new ResponseEntity<Restaurant>(service.deleteRestaurant(id),HttpStatus.OK);
    }

    @PutMapping("restaurant")
    public ResponseEntity<Restaurant> updateRestaurant(@Valid @RequestBody Restaurant restaurant)throws RestaurantNotFoundException {
        return new ResponseEntity<Restaurant>(service.updateRestaurant(restaurant),HttpStatus.OK);
    }

    @GetMapping("restaurant/loc/{location}")
    public ResponseEntity<Restaurant> getRestaurantByLocation(@PathVariable String location)throws  RestaurantNotFoundException{
        return new ResponseEntity<Restaurant>(service.findByLocation(location),HttpStatus.FOUND);
    }
    @GetMapping("restaurant/name/{name}")
    public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable String name)throws  RestaurantNotFoundException{
        return new ResponseEntity<Restaurant>(service.findByName(name),HttpStatus.FOUND);
    }
//fetch restaurant by menuItems

}
