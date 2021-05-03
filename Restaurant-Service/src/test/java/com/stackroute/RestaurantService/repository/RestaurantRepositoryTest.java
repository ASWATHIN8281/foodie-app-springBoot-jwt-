package com.stackroute.RestaurantService.repository;


import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.model.Restaurant;
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

    @Test
    public void shouldSaveRestaurantAndReturnRestaurant(){
        menuItems=new MenuItems(1,"Arabian","Al-fam","Spicy",120.0);
        List<MenuItems> menuItemsList=new ArrayList<>();
        menuItemsList.add(menuItems);
        Restaurant restaurant =new Restaurant(1,"BBQHUT","Chennai",menuItemsList);
        restaurantRepository.save(restaurant);
        Restaurant restaurant1=restaurantRepository.findById(restaurant.getRestaurantId()).get();
        assertEquals(restaurant.getName(),restaurant1.getName());
    }
    @Test
    public  void  shouldReturnListOfRestaurant(){
        menuItems=new MenuItems(1,"Arabian","Al-fam","Spicy",120.0);
        List<MenuItems> menuItemsList=new ArrayList<>();
        menuItemsList.add(menuItems);
        Restaurant restaurant =new Restaurant(1,"BBQHUT","Chennai",menuItemsList);
        Restaurant restaurant1 =new Restaurant(2,"ArabianNights","Hyderabad",menuItemsList);
        restaurantRepository.save(restaurant);
        restaurantRepository.save(restaurant1);
        List<Restaurant> restaurantList= (List<Restaurant>) restaurantRepository.findAll();
        assertEquals("ArabianNights",restaurantList.get(0).getName());
    }
    @Test
    public  void  GivenNameShouldReturnRestaurant(){
        menuItems=new MenuItems(1,"Arabian","Al-fam","Spicy",120.0);
        List<MenuItems> menuItemsList=new ArrayList<>();
        menuItemsList.add(menuItems);
        Restaurant restaurant =new Restaurant(1,"BBQHUT","Chennai",menuItemsList);
        Restaurant restaurant1=restaurantRepository.save(restaurant);
        Restaurant list=restaurantRepository.findByName(restaurant1.getName());
        assertEquals(restaurant.getRestaurantId(),list.getRestaurantId());
        assertEquals(restaurant1.getName(),list.getName());
        assertEquals(restaurant1.getLocation(),list.getLocation());

    }
    @Test
    public  void  GivenLocationShouldReturnRestaurant(){
        menuItems=new MenuItems(1,"Arabian","Al-fam","Spicy",120.0);
        List<MenuItems> menuItemsList=new ArrayList<>();
        menuItemsList.add(menuItems);
        Restaurant restaurant =new Restaurant(1,"BBQHUT","Chennai",menuItemsList);
        Restaurant restaurant1=restaurantRepository.save(restaurant);
        Restaurant list=restaurantRepository.findByLocation(restaurant1.getLocation());
        assertEquals(restaurant.getRestaurantId(),list.getRestaurantId());
        assertEquals(restaurant1.getName(),list.getName());
        assertEquals(restaurant1.getLocation(),list.getLocation());
    }
    @Test
    public  void  givenIdShouldDeleteRestaurant(){
        menuItems=new MenuItems(1,"Arabian","Al-fam","Spicy",120.0);
        List<MenuItems> menuItemsList=new ArrayList<>();
        menuItemsList.add(menuItems);
        Restaurant restaurant =new Restaurant(1,"BBQHUT","Chennai",menuItemsList);
        Restaurant restaurant1=restaurantRepository.save(restaurant);
        restaurantRepository.deleteById(restaurant1.getRestaurantId());
        Optional optional=restaurantRepository.findById(restaurant1.getRestaurantId());
        assertEquals(Optional.empty(),optional);
    }
}