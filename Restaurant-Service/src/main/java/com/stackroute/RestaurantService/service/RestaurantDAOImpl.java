package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.stackroute.RestaurantService.exception.RestaurantNotFoundException;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.model.Restaurant;
import com.stackroute.RestaurantService.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantDAOImpl implements RestaurantDAO {
    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant addRestaurant(Restaurant restaurant)throws RestaurantAlreadyExistsException {
        if(repository.existsByName(restaurant.getName())){
            throw new RestaurantAlreadyExistsException();
        }
        Restaurant restaurant1=repository.save(restaurant);
        return restaurant1;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }

    @Override
    public Restaurant deleteRestaurant(int id) throws RestaurantNotFoundException{
        Restaurant restaurant=null;
        Optional optional=repository.findById(id);
        if(!optional.isPresent()) {
            throw new RestaurantNotFoundException();
        }
            restaurant=repository.findById(id).get();
            repository.deleteById(id);
            return restaurant;

    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        Restaurant updatedRestaurant=null;
        //Optional optional=Optional.of(repository.findById(restaurant.getRestaurantId()));
        if(!repository.existsByName(restaurant.getName())) {
            throw new RestaurantNotFoundException();
        }
            Restaurant getRestaurant=repository.findById(restaurant.getRestaurantId()).get();
            getRestaurant.setName(restaurant.getName());
            getRestaurant.setLocation(restaurant.getLocation());
            updatedRestaurant=repository.save(getRestaurant);
            return updatedRestaurant;
    }



    @Override
    public Restaurant findByLocation(String location) throws RestaurantNotFoundException{
       //Optional optional=Optional.of(repository.findByLocation(location));
       if(!repository.existsByLocation(location)){
           throw new RestaurantNotFoundException();
       }
       Restaurant restaurant=repository.findByLocation(location);
       return restaurant;
    }

    @Override
    public Restaurant findByName(String name) throws RestaurantNotFoundException {
       // Optional optional=Optional.of(repository.findByName(name));
        if(!repository.existsByName(name)){
            throw  new RestaurantNotFoundException();
        }
        Restaurant restaurant=repository.findByName(name);
        return restaurant;
    }
}
