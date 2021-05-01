package com.stackroute.RestaurantService.service;
import com.stackroute.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.stackroute.RestaurantService.exception.RestaurantNotFoundException;
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
        if(repository.existsById(restaurant.getId())){
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
        Optional optional=Optional.of(repository.findByRestaurantId(id));
        if(!optional.isPresent()) {
            throw new RestaurantNotFoundException();
        }
            restaurant=repository.findByRestaurantId(id);
            repository.deleteById(id);
            return restaurant;

    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        Restaurant updatedRestaurant=null;
        Optional optional=Optional.of(repository.findByRestaurantId(restaurant.getId()));
        if(!optional.isPresent()) {
            throw new RestaurantNotFoundException();
        }
            Restaurant getRestaurant=repository.findByRestaurantId(restaurant.getId());
            getRestaurant.setRName(restaurant.getRName());
            getRestaurant.setLocation(restaurant.getLocation());
            updatedRestaurant=repository.save(getRestaurant);
            return updatedRestaurant;
    }

    @Override
    public Restaurant findByRestaurantId(int id) {
        return repository.findByRestaurantId(id);
    }

    @Override
    public Restaurant findByRestaurantLocation(String location) {
        return repository.findByRestaurantLocation(location);
    }
}
