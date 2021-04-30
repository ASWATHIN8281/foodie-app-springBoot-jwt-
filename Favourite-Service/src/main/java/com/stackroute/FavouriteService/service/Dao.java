package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.model.Favourite;

import java.util.List;

public interface Dao {
   public Favourite addFavourite(Favourite favourite);
   void deleteFavourite(String foodItem);

   List<Favourite> getAll();


}
