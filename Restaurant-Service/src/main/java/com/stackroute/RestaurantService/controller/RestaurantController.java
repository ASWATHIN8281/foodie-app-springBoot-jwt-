package com.stackroute.RestaurantService.controller;
import com.stackroute.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.stackroute.RestaurantService.exception.RestaurantNotFoundException;
import com.stackroute.RestaurantService.model.Restaurant;
import com.stackroute.RestaurantService.service.RestaurantDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class RestaurantController {
    @Autowired
    private RestaurantDAO service;
    @PostMapping("restaurant")
    public ResponseEntity<Restaurant>saveRestaurant(@RequestBody Restaurant restaurant) throws RestaurantAlreadyExistsException {
        return new ResponseEntity<Restaurant>(service.addRestaurant(restaurant), HttpStatus.CREATED);
    }
    @GetMapping("restaurants")
    public List<Restaurant>getAllRestaurants(){
        return service.getAllRestaurants();
    }
    @DeleteMapping("restaurant/{id}")
    public ResponseEntity<Restaurant>getRestaurantAfterDeleting(@PathVariable("id")int id)throws RestaurantNotFoundException {
        return new ResponseEntity<Restaurant>(service.deleteRestaurant(id),HttpStatus.OK);
    }

    @PutMapping("restaurant")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant)throws RestaurantNotFoundException {
        return new ResponseEntity<Restaurant>(service.updateRestaurant(restaurant),HttpStatus.OK);
    }

    @GetMapping("restaurant/{id}")
    public ResponseEntity<Restaurant>getRestaurantById(@PathVariable int id)throws RestaurantNotFoundException{
        return new ResponseEntity<Restaurant>(service.findByRestaurantId(id),HttpStatus.FOUND);
    }

    @GetMapping("restaurants/{location}")
    public ResponseEntity<Restaurant> getRestaurantByLocation(@PathVariable String location)throws  RestaurantNotFoundException{
        return new ResponseEntity<Restaurant>(service.findByRestaurantLocation(location),HttpStatus.FOUND);
    }


}
