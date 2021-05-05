package com.stackroute.FavouriteService.repository;

import com.stackroute.FavouriteService.model.Favourite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends MongoRepository<Favourite,Integer>{
    @Query(value = "{'foodItem':$0}", delete = true)
    Favourite deleteByfoodItem(String foodItem);
    Favourite findByFoodItem(String foodItem);
    Boolean existsByFoodItem(String foodItem);
    Boolean existsByUsername(String username);
    List<Favourite> findByUsername(String username);
    Favourite findByUsernameAndFoodItem(String username,String foodItem);
}
