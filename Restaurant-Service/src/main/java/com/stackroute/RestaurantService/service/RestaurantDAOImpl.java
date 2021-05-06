package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.MenuItemNotFoundException;
import com.stackroute.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.stackroute.RestaurantService.exception.RestaurantNotFoundException;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.model.Restaurant;
import com.stackroute.RestaurantService.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j

/**
 * @Service indicates annotated class is a service which hold business logic in the Service layer
 */

@Service
@AllArgsConstructor
public class RestaurantDAOImpl implements RestaurantDAO {

    /**
     * Constructor based Dependency injection to inject Restaurant Repository here
     */

    @Autowired
    private RestaurantRepository repository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(RestaurantDAOImpl.class);

    /**
     * Implementation of addRestaurant method
     */

    @Override
    public Restaurant addRestaurant(Restaurant restaurant)throws RestaurantAlreadyExistsException {
        if(repository.existsByName(restaurant.getName())){
            logger.error("Restaurant already exists");
            throw new RestaurantAlreadyExistsException();
        }
        Restaurant restaurant1=repository.save(restaurant);
        logger.info("New Restaurant added");
        return restaurant1;
    }
    /**
     * Implementation of getAllRestaurants method
     */

    @Override
    public List<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }
    /**
     * Implementation of deleteRestaurant method
     */
    @Override
    public Restaurant deleteRestaurant(int id) throws RestaurantNotFoundException{
        Restaurant restaurant=null;
        Optional optional=repository.findById(id);
        if(!optional.isPresent()) {
            logger.error("Restaurant not found");
            throw new RestaurantNotFoundException();
        }
            restaurant=repository.findById(id).get();
            repository.deleteById(id);
            logger.info("Restaurant deleted");
            return restaurant;

    }
    /**
     * Implementation of updateRestaurant method
     */
    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
        Restaurant updatedRestaurant=null;
        //Optional optional=Optional.of(repository.findById(restaurant.getRestaurantId()));
        if(!repository.existsByName(restaurant.getName())) {
            logger.error("Restaurant not found");
            throw new RestaurantNotFoundException();
        }
            /* Restaurant getRestaurant=repository.findById(restaurant.getRestaurantId()).get();
            getRestaurant.setName(restaurant.getName());
            getRestaurant.setLocation(restaurant.getLocation());*/
            updatedRestaurant=repository.save(restaurant);
            logger.error("Restaurant updated");
            return updatedRestaurant;
    }


    /**
     * Implementation of  findByLocation method
     */
    @Override
    public List<Restaurant> findByLocation(String location) throws RestaurantNotFoundException{
       //Optional optional=Optional.of(repository.findByLocation(location));
       if(!repository.existsByLocation(location)){
           logger.error("Restaurant not found");
           throw new RestaurantNotFoundException();
       }
       List<Restaurant> restaurant=repository.findByLocation(location);
       logger.info("Restaurant fetched based on location");
       return restaurant;
    }
    /**
     * Implementation of  findByName method
     */
    @Override
    public Restaurant findByName(String name) throws RestaurantNotFoundException {
       // Optional optional=Optional.of(repository.findByName(name));
        if(!repository.existsByName(name)){
            logger.error("Restaurant not found ");
            throw  new RestaurantNotFoundException();
        }
        Restaurant restaurant=repository.findByName(name);
        logger.info("Restaurant  fetched based on name");
        return restaurant;
    }
    /**
     * Implementation of searchByMenuItems  method
     */
    @Override
    public List<Restaurant> searchByMenuItems(String menuItem) throws MenuItemNotFoundException {
        Optional optional=Optional.of(repository.findByMenuItem(menuItem));
        List<Restaurant> restaurantList= repository.findByMenuItem(menuItem);
        if(!optional.isPresent() || restaurantList.isEmpty()){
            logger.error("Restaurant not found");
            throw new MenuItemNotFoundException();
        }
        List<Restaurant> restaurant=repository.findByMenuItem(menuItem);
        logger.info("Restaurant fetched based on menu item");
        return restaurant;
    }
}
