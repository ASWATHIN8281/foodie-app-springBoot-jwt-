package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.Exception.FavouriteListNotFoundException;
import com.stackroute.FavouriteService.Exception.FoodItemAlreadyExistsException;
import com.stackroute.FavouriteService.Exception.FoodItemNotFoundException;
import com.stackroute.FavouriteService.Exception.UsernameNotFoundException;
import com.stackroute.FavouriteService.model.Favourite;

import java.util.List;

public interface FavouriteDao {
   /**
    *
    * @param favourite
    * @return
    * @throws FoodItemAlreadyExistsException
    * These are the various methods implemented in the application.
    */
   public Favourite addFavourite(Favourite favourite) throws FoodItemAlreadyExistsException;
   public Favourite deleteFavourite(String username,String foodItem)throws FavouriteListNotFoundException;
   List<Favourite>  getFavouriteByUsername(String username) throws UsernameNotFoundException;
   List<Favourite> getAll();


}
