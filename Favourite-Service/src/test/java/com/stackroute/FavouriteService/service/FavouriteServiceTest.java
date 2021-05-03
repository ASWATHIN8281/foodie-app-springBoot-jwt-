package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.Exception.FoodItemAlreadyExistsException;
import com.stackroute.FavouriteService.Exception.FoodItemNotFoundException;
import com.stackroute.FavouriteService.model.Favourite;
import com.stackroute.FavouriteService.repository.FavouriteRepository;
import com.stackroute.FavouriteService.service.FavouriteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.mockito.Mockito.*;
import java.util.Optional;
import  java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;




@DataMongoTest
@ExtendWith(MockitoExtension.class)
class FavouriteServiceTest {
    @Mock
    private FavouriteRepository repository;
    @InjectMocks
    private FavouriteService service;
    private Favourite favourite, favourite1;
    private List<Favourite> favouriteList;
    private Optional optional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        favourite = new Favourite(1, "Tito's Cafe", "Junk", "Burger", 160);
        favourite1 = new Favourite(2, "Bamboo Restaurant", "Biryani", "Bamboo Chicken Biryani", 250);
        optional = Optional.of(favourite);
    }

    @AfterEach
    void tearDown() {
        favourite=null;
    }

    @Test
    public void givenFavouriteToSaveThenShouldReturnSavedFavourite() throws FoodItemAlreadyExistsException {
        when(repository.save(any())).thenReturn(favourite);
        service.addFavourite(favourite);
        verify(repository, times(1)).save(any());

    }
    @Test
    public void givenFavouriteToSaveThenShouldNotReturnSavedFavourite()  {
        when(repository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () ->
                service.addFavourite(favourite));

        verify(repository, times(1)).save(favourite);
    }

//    @Test
//    public void givenFavouriteToSaveThenShouldNotReturnSavedFavourite()
//            throws FoodItemAlreadyExistsException {
//        when(repository.save(favourite)).thenThrow(new FoodItemAlreadyExistsException());
//        Assertions.assertThrows(FoodItemAlreadyExistsException.class, () ->
//                service.addFavourite(favourite));
//
//        verify(repository, times(1)).save(favourite);
//    }






    @Test
    public void givenGetAllThenShouldReturnListOfAllFavourites() {
        repository.save(favourite);
        when(repository.findAll()).thenReturn(favouriteList);
        List<Favourite> favouriteList1 = service.getAll();
        assertEquals(favouriteList, favouriteList1);
        verify(repository, times(1)).save(favourite);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void givenFoodItemToDeleteThenShouldReturnDeletedFavouriteFoodItem() throws FoodItemNotFoundException {

        when(repository.findByFoodItem(favourite.getFoodItem())).thenReturn(favourite);

        Favourite deletedFavourite = service.deleteFavourite("Burger");
        assertEquals("Burger", deletedFavourite.getFoodItem());
        verify(repository, times(2)).findByFoodItem(favourite.getFoodItem());
        verify(repository, times(1)).deleteByfoodItem(favourite.getFoodItem());
    }
    @Test
    void givenFoodItemToDeleteThenShouldNotReturnDeletedFavourite() throws RuntimeException {
        when(repository.findByFoodItem(favourite.getFoodItem())).thenThrow( RuntimeException.class);
        Assertions.assertThrows(RuntimeException.class,() -> service.deleteFavourite("Burger"));

        verify(repository,times(1)).findByFoodItem(favourite.getFoodItem());

    }
//    @Test
//    void givenFoodItemToDeleteThenShouldNotReturnDeletedFavourite() throws FoodItemNotFoundException {
//        when(repository.findByFoodItem(favourite.getFoodItem())).thenThrow( FoodItemNotFoundException.class);
//        Assertions.assertThrows(FoodItemNotFoundException.class,() -> service.deleteFavourite("Burger"));
//
//        verify(repository,times(1)).findByFoodItem(favourite.getFoodItem());
//
//    }



    }
