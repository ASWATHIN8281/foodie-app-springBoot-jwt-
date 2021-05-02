package com.stackroute.FavouriteService.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FavouriteTest {

    Favourite favourite;

    @BeforeEach
    void setUp() {
        favourite=new Favourite();
    }

    @Test
    public void shouldGetId(){
        favourite.setId(2);
        assertEquals(2,favourite.getId());
    }
    @Test
    public void shouldGetRestaurantName(){
        favourite.setRestaurantName("Clark's Village Restaurant");
        assertEquals("Clark's Village Restaurant",favourite.getRestaurantName());
    }
    @Test
    public void shouldGetCategory(){
        favourite.setCategory("South Indian");
        assertEquals("South Indian",favourite.getCategory());
    }
    @Test
    public void shouldGetFoodItem(){
        favourite.setFoodItem("Masala Dosa");
        assertEquals("Masala Dosa",favourite.getFoodItem());
    }
    @Test
    public void shouldGetPrice(){
        favourite.setPrice(80);
        assertEquals(80,favourite.getPrice());
    }






}