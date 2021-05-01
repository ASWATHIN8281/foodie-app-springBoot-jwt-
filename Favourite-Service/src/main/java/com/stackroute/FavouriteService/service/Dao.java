package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.Exception.FoodItemAlreadyExistsException;
import com.stackroute.FavouriteService.model.Favourite;
import com.stackroute.FavouriteService.model.FavouriteDto;

import java.util.List;

public interface Dao {
   public Favourite addFavourite(FavouriteDto favouriteDto) throws FoodItemAlreadyExistsException;
   public Favourite deleteFavourite(String foodItem);

   List<Favourite> getAll();


}
