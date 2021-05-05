package com.stackroute.RestaurantService.repository;

import com.stackroute.RestaurantService.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 * @Repository marks the specific class as a Data Access Object
 */

@Repository
public interface OrderRepository extends MongoRepository<Order,Integer> {


}
