package com.stackroute.FavouriteService.repository;

import com.stackroute.FavouriteService.model.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends MongoRepository<Favourite,Integer>{
    @Query(value = "{'foodItem':$0}", delete = true)
    Favourite deleteByfoodItem(String foodItem);
    Favourite findByFoodItem(String foodItem);
    Boolean existsByFoodItem(String foodItem);
}
