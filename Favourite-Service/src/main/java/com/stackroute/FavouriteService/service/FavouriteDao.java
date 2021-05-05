package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.Exception.FoodItemAlreadyExistsException;
import com.stackroute.FavouriteService.Exception.FoodItemNotFoundException;
import com.stackroute.FavouriteService.Exception.UsernameNotFoundException;
import com.stackroute.FavouriteService.model.Favourite;

import java.util.List;

public interface FavouriteDao {
   public Favourite addFavourite(Favourite favourite) throws FoodItemAlreadyExistsException;
   public Favourite deleteFavourite(String username,String foodItem)throws FoodItemNotFoundException;
   List<Favourite>  getFavouriteByUsername(String username) throws UsernameNotFoundException;

   List<Favourite> getAll();


}
