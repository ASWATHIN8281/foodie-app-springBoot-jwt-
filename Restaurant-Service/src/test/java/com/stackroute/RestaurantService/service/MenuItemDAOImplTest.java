package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.MenuItemAlreadyExistsException;
import com.stackroute.RestaurantService.exception.MenuItemNotFoundException;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.repository.MenuItemsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuItemDAOImplTest {
    @Mock
    private MenuItemsRepository repository;
    @InjectMocks
    private MenuItemDAOImpl service;
    private MenuItems menuItems;
    private List<MenuItems> menuItemsList;
    Optional optional;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        menuItems=new MenuItems(1,"Arabian","Alfam","spicy-food",150.0);
        optional=Optional.of(menuItems);
    }

    @AfterEach
    void tearDown() {
        menuItems=null;
    }
    @Test
    public void  givenMenuItemsToSaveAndReturnMenuItem() throws MenuItemAlreadyExistsException {

        when(repository.save(any())).thenReturn(menuItems);
        service.addMenuItems(menuItems);
        verify(repository,times(1)).save(any());
    }
    @Test
    public void givenGetAllShouldReturnListOfAllMenuItems(){
        repository.save(menuItems);
        when(repository.findAll()).thenReturn(menuItemsList);
        List<MenuItems> menuItemsList1=service.getAllMenuItems();
        assertEquals(menuItemsList1,menuItemsList);
        verify(repository,times(1)).save(menuItems);
        verify(repository,times(1)).findAll();
    }
    @Test
    public void givenMenuItemToSaveThenShouldNotReturnSavedMenuItem() {
        when(repository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class,() -> {
            service.addMenuItems(menuItems);
        });
        verify(repository, times(1)).save(any());
    }
//    @Test
//    void givenMenuItemNameToDeleteThenShouldReturnDeletedMenuItem() throws MenuItemNotFoundException {
//        when(repository.existsByName(menuItems.getName())).thenReturn(true);
//        MenuItems deleted=service.deleteMenuItems(menuItems.getName());
//        assertEquals(1,deleted.getMenuId());
//        //verify(repository,times(2)).findById(soulmate.getId());
//        verify(repository, times(1)).existsByName(menuItems.getName());
//        verify(repository,times(1)).deleteMenuItem(menuItems.getName());
//    }
    @Test
    public void givenMenuItemToUpdateThenShouldReturnUpdatedMenuItem() throws MenuItemNotFoundException {
        when(repository.existsByName(menuItems.getName())).thenReturn(true);
        when(repository.save(menuItems)).thenReturn(menuItems);
        menuItems.setPrice(118.0);
        MenuItems menuItems1= service.updateMenuItems(menuItems);
        assertEquals(menuItems1.getPrice(), 118.0);
        verify(repository, times(1)).save(menuItems);
        verify(repository, times(1)).existsByName(menuItems.getName());

    }
}