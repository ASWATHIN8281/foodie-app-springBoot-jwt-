package com.stackroute.FavouriteService.repository;

import com.stackroute.FavouriteService.model.Favourite;
import com.stackroute.FavouriteService.repository.FavouriteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class FavouriteRepositoryTest {
    @Autowired
    private FavouriteRepository repository;


    @Test
    public void givenFavouriteToSaveThenShouldReturnSavedFavourite(){
        Favourite favourite=new Favourite(2,"Tito's Cafe","Junk","Burger",160,"Jenis");
        repository.save(favourite);
        Favourite favourite1=repository.findById(favourite.getId()).get();

        assertNotNull(favourite1);
        assertEquals(favourite1.getFoodItem(),favourite1.getFoodItem());
    }
    @Test
    public void givenGetAllThenShouldReturnListOfAllFavourites(){
        Favourite favourite=new Favourite(1,"BarbequeIn","Arabian","Mandi",160,"Alby" );
        repository.save(favourite);
        Favourite favourite1=new Favourite(3,"Navaratna","Thali's","Veg Thali",150,"Alby");
        repository.save(favourite1);
        List<Favourite>favouriteList=repository.findAll();
        assertEquals("BarbequeIn",favouriteList.get(0).getRestaurantName());
    }
    @Test
    public void givenFoodItemThenShouldReturnRespectiveFavourite(){
        Favourite favourite=new Favourite(15,"Panipuri Corner","StreetFood","Panipuri",45,"Jenis");
        repository.save(favourite);
        Favourite favourite1=repository.findByFoodItem(favourite.getFoodItem());
        assertEquals("Panipuri",favourite1.getFoodItem());
    }
    @Test
    public void givenFoodItemToDeleteThenShouldReturnDeletedFavourite(){
        Favourite favourite=new Favourite(16,"Panipuri Corner","StreetFood","Masala Puri",30,"Jenis");
        repository.save(favourite);
        repository.deleteByfoodItem(favourite.getFoodItem());
        Favourite favourite2=repository.findByFoodItem(favourite.getFoodItem());
        assertEquals("Masala Puri",favourite2.getFoodItem());
    }
}