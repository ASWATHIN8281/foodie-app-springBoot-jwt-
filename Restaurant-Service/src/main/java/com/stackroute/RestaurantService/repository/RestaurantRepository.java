package com.stackroute.RestaurantService.repository;

import com.stackroute.RestaurantService.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Repository marks the specific class as a Data Access Object
 */

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant,Integer> {
    List<Restaurant> findByLocation(String location);
    Restaurant findByName(String name);
    boolean existsByName(String RestaurantName);
    boolean existsByLocation(String location);
    @Query("{'menuItemsList':{ $elemMatch: {'name':?0 }}}")
    List<Restaurant> findByMenuItem(String menuItem);
}
