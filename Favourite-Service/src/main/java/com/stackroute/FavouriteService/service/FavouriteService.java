package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.Exception.FoodItemAlreadyExistsException;
import com.stackroute.FavouriteService.model.Favourite;
import com.stackroute.FavouriteService.model.FavouriteDto;
import com.stackroute.FavouriteService.repository.FavouriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavouriteService implements Dao {
    @Autowired
    private FavouriteRepository repository;


    @Override
    public Favourite addFavourite(FavouriteDto favouriteDto)throws FoodItemAlreadyExistsException

    {
        if (repository.existsByFoodItem(favouriteDto.getFoodItem())){
            throw new FoodItemAlreadyExistsException();
        }
        Favourite favourite=new Favourite();
        favourite.setId(favouriteDto.getId());
        favourite.setRestaurantName(favouriteDto.getRestaurantName());
        favourite.setFoodItem(favouriteDto.getFoodItem());
        favourite.setPrice(favouriteDto.getPrice());
        favourite.setCategory(favouriteDto.getCategory());
        return repository.save(favourite);
    }

    @Override
    public Favourite deleteFavourite(String foodItem) {
        Favourite favourite=null;
        Optional optional=Optional.of(repository.findByFoodItem(foodItem));
        if (optional.isPresent()){
            favourite=repository.findByFoodItem(foodItem);
            repository.deleteByfoodItem(foodItem);
        }
       return favourite;
    }

    @Override
    public List<Favourite> getAll(){
        return (List<Favourite>) repository.findAll();
    }
}
