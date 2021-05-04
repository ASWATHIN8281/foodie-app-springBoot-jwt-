package com.stackroute.RestaurantService.repository;


import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.model.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataMongoTest

class RestaurantRepositoryTest {
    @Autowired
    private RestaurantRepository restaurantRepository;

    private MenuItems menuItems;
    private List<MenuItems>menuItemsList;
    private  Restaurant restaurant;
    private Restaurant restaurant1;

    @BeforeEach
    void setUp() {
        menuItems=new MenuItems(1,"Arabian","Al-fam","Spicy",120.0);
        restaurant =new Restaurant();
        restaurant.setRestaurantId(1);
        restaurant.setName("MagicKitchen");
        restaurant.setLocation("Hyderabad");
        restaurant.setMenuItemsList(menuItemsList);
        restaurant1=new Restaurant();
        restaurant1.setRestaurantId(2);
        restaurant1.setName("SpicyFood");
        restaurant1.setLocation("Pune");
        restaurant1.setMenuItemsList(menuItemsList);


    }

    @AfterEach
    void tearDown() {
        restaurant=null;
        restaurant1=null;
        menuItems=null;
    }

    @Test
    public void shouldSaveRestaurantAndReturnRestaurant(){
        restaurantRepository.save(restaurant);
        Restaurant restaurant1=restaurantRepository.findById(restaurant.getRestaurantId()).get();
        assertEquals(restaurant.getName(),restaurant1.getName());
    }
    @Test
    public  void  shouldReturnListOfRestaurant(){

        restaurantRepository.save(restaurant);
        List<Restaurant> restaurantList= (List<Restaurant>) restaurantRepository.findAll();
        assertEquals("MagicKitchen",restaurantList.get(0).getName());
    }
    @Test
    public  void  GivenNameShouldReturnRestaurant(){
        restaurantRepository.save(restaurant);
        restaurantRepository.save(restaurant1);
        Restaurant list=restaurantRepository.findByName(restaurant1.getName());
        assertEquals(restaurant1.getRestaurantId(),list.getRestaurantId());
        assertEquals(restaurant1.getName(),list.getName());
        assertEquals(restaurant1.getLocation(),list.getLocation());

    }
    @Test
    public  void  GivenLocationShouldReturnRestaurant(){
        restaurantRepository.save(restaurant);
        restaurantRepository.save(restaurant1);
        Restaurant list=restaurantRepository.findByLocation(restaurant1.getLocation());
        assertEquals(restaurant1.getRestaurantId(),list.getRestaurantId());
        assertEquals(restaurant1.getName(),list.getName());
        assertEquals(restaurant1.getLocation(),list.getLocation());
    }
    @Test
    public  void  givenIdShouldDeleteRestaurant(){

        restaurantRepository.save(restaurant);
        restaurantRepository.save(restaurant1);
        restaurantRepository.deleteById(restaurant1.getRestaurantId());
        Optional optional=restaurantRepository.findById(restaurant1.getRestaurantId());
        assertEquals(Optional.empty(),optional);
    }
}