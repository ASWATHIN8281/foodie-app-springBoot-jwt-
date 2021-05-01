package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.stackroute.RestaurantService.exception.RestaurantNotFoundException;
import com.stackroute.RestaurantService.model.Restaurant;

import java.util.List;

public interface RestaurantDAO {
    Restaurant addRestaurant(Restaurant restaurant)throws RestaurantAlreadyExistsException;
    List <Restaurant>getAllRestaurants();
    Restaurant deleteRestaurant(int id)throws RestaurantNotFoundException;
    Restaurant updateRestaurant(Restaurant restaurant)throws RestaurantNotFoundException;
    Restaurant findByRestaurantId(int id) throws RestaurantNotFoundException;
    Restaurant findByRestaurantLocation(String location)throws RestaurantNotFoundException;
}
