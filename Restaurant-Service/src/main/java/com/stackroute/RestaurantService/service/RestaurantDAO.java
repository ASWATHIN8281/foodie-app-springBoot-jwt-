package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.MenuItemNotFoundException;
import com.stackroute.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.stackroute.RestaurantService.exception.RestaurantNotFoundException;
import com.stackroute.RestaurantService.model.Restaurant;

import java.util.List;

public interface RestaurantDAO {
    Restaurant addRestaurant(Restaurant restaurant)throws RestaurantAlreadyExistsException;
    List <Restaurant>getAllRestaurants();
    Restaurant deleteRestaurant(int id)throws RestaurantNotFoundException;
    Restaurant updateRestaurant(Restaurant restaurant)throws RestaurantNotFoundException;
    Restaurant findByLocation(String location)throws RestaurantNotFoundException;
    Restaurant findByName(String name)throws RestaurantNotFoundException;
    List<Restaurant> searchByMenuItems(String menuItem) throws MenuItemNotFoundException;
}
