package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.Exception.FoodItemAlreadyExistsException;
import com.stackroute.FavouriteService.Exception.FoodItemNotFoundException;
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
@Slf4j
@Service
@AllArgsConstructor
public class FavouriteService implements FavouriteDao {
    @Autowired
    private FavouriteRepository repository;

    private static final Logger logger= LoggerFactory.getLogger(FavouriteController.class);

    @Override
    public Favourite addFavourite(Favourite favourite)throws FoodItemAlreadyExistsException

    {
        if (repository.existsByFoodItem(favourite.getFoodItem())){
            throw new FoodItemAlreadyExistsException();
        }
//        Favourite favourite=new Favourite();
//        favourite.setId(favouriteDto.getId());
//        favourite.setRestaurantName(favouriteDto.getRestaurantName());
//        favourite.setFoodItem(favouriteDto.getFoodItem());
//        favourite.setPrice(favouriteDto.getPrice());
//        favourite.setCategory(favouriteDto.getCategory());

        logger.info("The FoodItem is added to Favourite List");
        return repository.save(favourite);
    }

    @Override
    public Favourite deleteFavourite(String foodItem)throws FoodItemNotFoundException {

        Favourite favourite=null;

        if (!repository.existsByFoodItem(foodItem)){
            throw new FoodItemNotFoundException();
        }

            favourite=repository.findByFoodItem(foodItem);
            repository.deleteByfoodItem(foodItem);
logger.info("The foodItem is deleted.");
       return favourite;
    }

    @Override
    public List<Favourite> getAll(){
        logger.info("This is the List of Favourite FoodItem.");
        return (List<Favourite>) repository.findAll();
    }
}
