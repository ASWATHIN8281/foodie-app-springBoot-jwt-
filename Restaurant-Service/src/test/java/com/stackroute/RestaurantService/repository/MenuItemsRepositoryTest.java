package com.stackroute.RestaurantService.repository;

import com.stackroute.RestaurantService.model.MenuItems;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataMongoTest
class MenuItemsRepositoryTest {
    @Autowired
    private MenuItemsRepository repository;

    private MenuItems menuItems;

    @BeforeEach
    void setUp() {
        menuItems=new MenuItems(1,"Arabian","Alfam","spicy food",190.0);
    }

    @AfterEach
    void tearDown() {
        menuItems=null;
    }
    @Test
    public void shouldSaveMenuItemAndReturnMenuItem(){
        repository.save(menuItems);
        MenuItems menuItems1=repository.findById(menuItems.getMenuId()).get();
        assertEquals(menuItems1.getName(),menuItems.getName());
    }
    @Test
    public  void  GivenMenuItemNameShouldReturnMenuItem(){
        MenuItems menuItems1=repository.save(menuItems);
        MenuItems list=repository.findByName(menuItems1.getName());
        assertEquals(menuItems1.getMenuId(),list.getMenuId());
        assertEquals(menuItems1.getName(),list.getName());
        assertEquals(menuItems1.getCategory(), list.getCategory());
        assertEquals(menuItems1.getDescription(),list.getDescription());
    }
    @Test
    public  void  givenMenuItemShouldDeleteMenuItem(){
//        MenuItems menuItems1=repository.save(menuItems);
//        repository.deleteByname(menuItems1.getName());
//        //Optional optional=Optional.of(repository.findByName(menuItems1.getName()));
//        assertEquals(false,repository.existsByName(menuItems1.getName()));
        repository.save(menuItems);
        MenuItems menuItems2=repository.findByName(menuItems.getName());
        repository.deleteByname(menuItems.getName());
        assertEquals("Alfam",menuItems2.getName());
    }
    @Test
    public void givenGetAllThenShouldReturnListOfAllMenuItem(){
        repository.save(menuItems);
        List<MenuItems> list=repository.findAll();
        assertEquals("Arabian",list.get(0).getCategory());
    }
}