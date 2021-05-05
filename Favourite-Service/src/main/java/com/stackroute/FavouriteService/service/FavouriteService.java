package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.Exception.FoodItemAlreadyExistsException;
import com.stackroute.FavouriteService.Exception.FoodItemNotFoundException;
import com.stackroute.FavouriteService.Exception.UsernameNotFoundException;
import com.stackroute.FavouriteService.controller.FavouriteController;
import com.stackroute.FavouriteService.model.Favourite;
import com.stackroute.FavouriteService.repository.FavouriteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Slf4j is used to implement logging
 * @Service is used to declare the class as service layer
 * @AllArgsConstructor generates a constructor with 1 parameter for each field
 */
@Slf4j
@Service
@AllArgsConstructor
public class FavouriteService implements FavouriteDao {
    //autowires the repository layer
    @Autowired
    private FavouriteRepository repository;

    private static final Logger logger= LoggerFactory.getLogger(FavouriteController.class);

    /**
     *
     * @param favourite
     * The method used to add Favourites of the customer to Favourite
     * The logger will log the required info
     * @throws FoodItemAlreadyExistsException if the given fooditem already exists in customers favouritelist
     */
    @Override
    public Favourite addFavourite(Favourite favourite)throws FoodItemAlreadyExistsException

    {
        if (repository.existsByFoodItem(favourite.getFoodItem())){
            if(repository.existsByUsername(favourite.getUsername())){
            throw new FoodItemAlreadyExistsException();}
        }


        logger.info("The FoodItem is added to Favourite List");
        return repository.save(favourite);
    }

    /**
     *
     * @param username
     * @param foodItem
     * The method deletes the favourite of customer using the username and fooditem
     *  returns the deleted favourite
     *  The logger will log the required info
     * @throws FoodItemNotFoundException if the given fooditem is not found as favourite in the favouritelist
     */

    @Override
    public Favourite deleteFavourite(String username,String foodItem)throws FoodItemNotFoundException {
        Favourite favourite=null;
        Optional optional=Optional.of(repository.findByUsernameAndFoodItem(username, foodItem));
        if (!optional.isPresent()){
            throw new FoodItemNotFoundException();
        }
        favourite=repository.findByUsernameAndFoodItem(username, foodItem);
        repository.delete(favourite);
        logger.info("The foodItem is deleted.");
        return favourite;
    }

    /**
     *
     * @param username
     * This method gets the customer favourite using the username of customer
     * The logger will log the required info
     * @throws UsernameNotFoundException if the given username is not found in the database
     */
    @Override
    public List<Favourite> getFavouriteByUsername(String username)throws UsernameNotFoundException {
        if (!repository.existsByUsername(username)){
            throw new UsernameNotFoundException();

        }
        logger.info("This is the list of Favourites  of User");
        List<Favourite> favourite= repository.findByUsername(username);
        return favourite;
    }

    /**
     * This method gets the favourite of all customer
     * The logger will log the required info
     */
    @Override
    public List<Favourite> getAll(){
        logger.info("This is the List of Favourite FoodItem.");
        return (List<Favourite>) repository.findAll();
    }
}
