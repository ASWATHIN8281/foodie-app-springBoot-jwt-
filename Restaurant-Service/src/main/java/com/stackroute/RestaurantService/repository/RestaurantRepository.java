package com.stackroute.RestaurantService.repository;

import com.stackroute.RestaurantService.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant,Integer> {
    Restaurant findByRestaurantId(int id);
    Restaurant findByRestaurantLocation(String location);
}
