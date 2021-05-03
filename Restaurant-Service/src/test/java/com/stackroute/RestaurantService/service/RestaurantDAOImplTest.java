package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.stackroute.RestaurantService.exception.RestaurantNotFoundException;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.model.Restaurant;
import com.stackroute.RestaurantService.repository.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantDAOImplTest {
    @Mock
    private RestaurantRepository repository;
    @InjectMocks
    private RestaurantDAOImpl restaurantDAO;
    private Restaurant restaurant;
    private  MenuItems menuItems;
    private List<Restaurant> restaurantList;
    private  List<MenuItems>menuItemsList;
    Optional optional;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        menuItems=new MenuItems(1,"Arabian","Al-fam","Spicy",120.0);
        List<MenuItems> menuItemsList=new ArrayList<>();
        menuItemsList.add(menuItems);
        restaurant=new Restaurant(1,"Taj","Hyderabad",menuItemsList);
        optional=Optional.of(restaurant);
    }
    @AfterEach
    void tearDown() {
        restaurant=null;
        menuItems=null;
    }
    @Test
    public void  givenRestaurantToSaveAndReturnRestaurant() throws RestaurantAlreadyExistsException {

        when(repository.save(any())).thenReturn(restaurant);
        restaurantDAO.addRestaurant(restaurant);
        verify(repository,times(1)).save(any());
    }
    @Test
    public void givenGetAllShouldReturnListOfAllRestaurants(){
        repository.save(restaurant);
        when(repository.findAll()).thenReturn(restaurantList);
        List<Restaurant> restaurantList1=restaurantDAO.getAllRestaurants();
        assertEquals(restaurantList1,restaurantList);
        verify(repository,times(1)).save(restaurant);
        verify(repository,times(1)).findAll();
    }
    @Test
    public void givenRestaurantToSaveThenShouldNotReturnSavedRestaurant() {
        when(repository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class,() -> {
            restaurantDAO.addRestaurant(restaurant);
        });
        verify(repository, times(1)).save(any());
    }
    @Test
    public void givenRestaurantToUpdateThenShouldReturnUpdatedRestaurant() throws RestaurantNotFoundException {
        when(repository.existsByName(restaurant.getName())).thenReturn(true);
        when(repository.save(restaurant)).thenReturn(restaurant);
        menuItems.setPrice(118.0);
        Restaurant restaurant1= restaurantDAO.updateRestaurant(restaurant);
        assertEquals(restaurant1.getLocation(), "Hyderabad");
        verify(repository, times(1)).save(restaurant);
        verify(repository, times(1)).existsByName(restaurant.getName());

    }
}