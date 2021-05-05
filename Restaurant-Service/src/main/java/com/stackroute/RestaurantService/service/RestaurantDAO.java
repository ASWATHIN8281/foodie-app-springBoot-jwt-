package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.MenuItemNotFoundException;
import com.stackroute.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.stackroute.RestaurantService.exception.RestaurantNotFoundException;
import com.stackroute.RestaurantService.model.Restaurant;

import java.util.List;

public interface RestaurantDAO {
    /**
     * AbstractMethod to save a restaurant
     */

    Restaurant addRestaurant(Restaurant restaurant)throws RestaurantAlreadyExistsException;
    /**
     * AbstractMethod to retrieve all restaurants
     */

    List <Restaurant>getAllRestaurants();
    /**
     * AbstractMethod to delete restaurant
     */

    Restaurant deleteRestaurant(int id)throws RestaurantNotFoundException;
    /**
     * AbstractMethod to update restaurant
     */

    Restaurant updateRestaurant(Restaurant restaurant)throws RestaurantNotFoundException;
    /**
     * AbstractMethod to find restaurant by location
     */

    List<Restaurant> findByLocation(String location)throws RestaurantNotFoundException;
    /**
     * AbstractMethod to find restaurant by name
     */
    Restaurant findByName(String name)throws RestaurantNotFoundException;
    /**
     * AbstractMethod to find restaurant by food item
     */
    List<Restaurant> searchByMenuItems(String menuItem) throws MenuItemNotFoundException;
}
