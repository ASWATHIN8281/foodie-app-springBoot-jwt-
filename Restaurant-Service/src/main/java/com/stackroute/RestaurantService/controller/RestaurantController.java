package com.stackroute.RestaurantService.controller;
import com.stackroute.RestaurantService.exception.MenuItemNotFoundException;
import com.stackroute.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.stackroute.RestaurantService.exception.RestaurantNotFoundException;
import com.stackroute.RestaurantService.model.Order;
import com.stackroute.RestaurantService.model.Restaurant;
import com.stackroute.RestaurantService.service.RestaurantDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class RestaurantController {
    @Autowired
    private RestaurantDAO service;
    private static Logger logger = (Logger) LoggerFactory.getLogger(RestaurantController.class);

    @PostMapping("restaurant")
    public ResponseEntity<Restaurant>saveRestaurant(@Valid @RequestBody Restaurant restaurant) throws RestaurantAlreadyExistsException {
        logger.info("Restaurant added");
        return new ResponseEntity<Restaurant>(service.addRestaurant(restaurant), HttpStatus.CREATED);
    }
    @GetMapping("restaurants")
    public ResponseEntity<List<Restaurant>> getAllOrders() {
        logger.info("Fetched all restaurants");
        return new ResponseEntity<List<Restaurant>>((List<Restaurant>) service.getAllRestaurants(), HttpStatus.OK);
    }
    @DeleteMapping("restaurant/{id}")
    public ResponseEntity<Restaurant>getRestaurantAfterDeleting(@PathVariable("id")int id)throws RestaurantNotFoundException {
        logger.info("Restaurant deleted");
        return new ResponseEntity<Restaurant>(service.deleteRestaurant(id),HttpStatus.OK);
    }

    @PutMapping("restaurant")
    public ResponseEntity<Restaurant> updateRestaurant(@Valid @RequestBody Restaurant restaurant)throws RestaurantNotFoundException {
        logger.info("Restaurant updated");
        return new ResponseEntity<Restaurant>(service.updateRestaurant(restaurant),HttpStatus.OK);
    }

    @GetMapping("restaurant/loc/{location}")
    public ResponseEntity<List<Restaurant> >getRestaurantByLocation(@PathVariable String location)throws  RestaurantNotFoundException{
       logger.info("Fetched restaurant based on location");
        return new ResponseEntity<List<Restaurant>>(service.findByLocation(location),HttpStatus.FOUND);
    }
    @GetMapping("restaurant/name/{name}")
    public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable String name)throws  RestaurantNotFoundException{
        logger.info("Fetched restaurant based on location");
        return new ResponseEntity<Restaurant>(service.findByName(name),HttpStatus.FOUND);
    }
//fetch restaurant by menuItems
    @GetMapping("restaurant/menuitem/{item}")
    public ResponseEntity<List<Restaurant>> searchRestaurantByItem(@PathVariable String item) throws RestaurantNotFoundException, MenuItemNotFoundException {
    logger.info("Fetched restaurant based on name");
        return new ResponseEntity<>(service.searchByMenuItems(item),HttpStatus.FOUND);
}
}
