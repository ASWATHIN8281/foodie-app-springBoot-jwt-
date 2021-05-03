package com.stackroute.RestaurantService.repository;
import com.stackroute.RestaurantService.model.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest

@SpringBootTest

public class RestaurantIntegrationTest {

    @Autowired
    private RestaurantRepository restaurantRepository;
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurant= new Restaurant();
        restaurant.setRestaurantId(1);
        restaurant.setName("Donalds");
        restaurant.setLocation("Mysore");
    }
    @AfterEach
    public void tearDown() {
        restaurantRepository.deleteAll();
        restaurant = null;
    }

    @Test
    public void givenRestaurantToSaveThenShouldReturnSavedRestaurant() {
        restaurantRepository.save(restaurant);
        Restaurant fetchedRestaurant = restaurantRepository.findById(restaurant.getRestaurantId()).get();
        assertEquals(1, fetchedRestaurant.getRestaurantId());
    }
    @Test
    public void givenGetAllRestaurantsThenShouldReturnListOfAllRestaurants() {
        Restaurant restaurant = new Restaurant(2, "Dhaba","Chennai");
        Restaurant restaurant1 = new Restaurant(3, "TastyKitchen","Hyderabad");

        restaurantRepository.save(restaurant);
        restaurantRepository.save(restaurant1);

        List<Restaurant> restaurantList = (List<Restaurant>) restaurantRepository.findAll();
        assertEquals("TastyKitchen", restaurantList.get(1).getName());
    }

    @Test
    public void givenRestaurantNameThenShouldReturnRespectiveRestaurant() {
        Restaurant restaurant = new Restaurant(4,"FunKitchen","Bangalore");
        Restaurant restaurant1 = restaurantRepository.save(restaurant);
        Optional<Restaurant> optional = Optional.of(restaurantRepository.findByName(restaurant1.getName()));
        assertEquals(restaurant1.getRestaurantId(), optional.get().getRestaurantId());
        assertEquals(restaurant1.getName(), optional.get().getName());
        assertEquals(restaurant1.getLocation(), optional.get().getLocation());

    }
    @Test
    public void givenRestaurantIdToDeleteThenShouldReturnDeletedRestaurant() {
        Restaurant restaurant = new Restaurant(2, "Dhaba","Chennai");
        restaurantRepository.save(restaurant);
        restaurantRepository.deleteById(restaurant.getRestaurantId());
        Optional optional = restaurantRepository.findById(restaurant.getRestaurantId());
        assertEquals(Optional.empty(), optional);
    }





}
