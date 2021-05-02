package com.stackroute.RestaurantService.repository;
import com.stackroute.RestaurantService.model.MenuItems;
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


@SpringBootTest
public class MenuItemsIntegrationTest {
    @Autowired
    private MenuItemsRepository menuItemsRepository;
    private MenuItems menuItems;

    @BeforeEach
    public void setUp() {
        menuItems=new MenuItems();
        menuItems.setMenuId(1);
        menuItems.setCategory("Veg");
        menuItems.setName("Masaladosa");
        menuItems.setDescription("A dish made of dosa with added sides");
        menuItems.setPrice(60.0);

    }
    @AfterEach
    public void tearDown() {
        menuItemsRepository.deleteAll();
        menuItems = null;
    }

    @Test
    public void givenMenuItemsToSaveThenShouldReturnSavedMenuItems() {
        menuItemsRepository.save(menuItems);
       MenuItems fetchedMenuItems = menuItemsRepository.findById(menuItems.getMenuId()).get();
        assertEquals(1, fetchedMenuItems.getMenuId());
    }

    @Test
    public void givenMenuItemNameThenShouldReturnRespectiveMenuItem() {
        MenuItems menuItems = new MenuItems(1,"veg","masaladosa","goodrefresh",120);
        MenuItems menuItems1 = menuItemsRepository.save(menuItems);
        Optional<MenuItems> optional = Optional.of(menuItemsRepository.findByName(menuItems1.getName()));
        assertEquals(menuItems1.getMenuId(), optional.get().getMenuId());
        assertEquals(menuItems1.getCategory(), optional.get().getCategory());
        assertEquals(menuItems1.getName(), optional.get().getName());
        assertEquals(menuItems1.getDescription(), optional.get().getDescription());
        assertEquals(menuItems1.getPrice(), optional.get().getPrice());
    }
    @Test
    public void givenMenuNameToDeleteThenShouldReturnDeletedMenu() {
        MenuItems menuItems = new MenuItems(1,"veg","masaladosa","goodrefresh",120);
        menuItemsRepository.save(menuItems);
        menuItemsRepository.deleteMenuItem(menuItems.getName());
        Optional optional = Optional.of(menuItemsRepository.findByName(menuItems.getName()));
        assertEquals(Optional.empty(), optional);
    }
}
