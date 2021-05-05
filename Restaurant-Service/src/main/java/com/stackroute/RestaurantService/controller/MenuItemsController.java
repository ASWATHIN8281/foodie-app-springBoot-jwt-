package com.stackroute.RestaurantService.controller;

import com.stackroute.RestaurantService.exception.MenuItemAlreadyExistsException;
import com.stackroute.RestaurantService.exception.MenuItemNotFoundException;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.service.MenuItemDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
@Slf4j
@AllArgsConstructor
/**
 * RestController annotation is used to create Restful web services using Spring MVC
 */

@RestController
/**
 * RequestMapping annotation maps HTTP requests to handler methods
 */


@RequestMapping("api/v1")
public class MenuItemsController {
    @Autowired
    private MenuItemDAO service;
    private static Logger logger = (Logger) LoggerFactory.getLogger(MenuItemsController.class);

    /**
     * save a new menu-item
     */

    @PostMapping("menuitem")
    public ResponseEntity<MenuItems> saveNewMenu(@Valid @RequestBody MenuItems menuItems) throws MenuItemAlreadyExistsException {
        logger.info("Menuitem added");
        return new ResponseEntity<MenuItems>(service.addMenuItems(menuItems), HttpStatus.CREATED);
    }
    /**
     * retrieve all menu-items
     */

    @GetMapping("menuitems")
    public ResponseEntity<List<MenuItems>> getAllMenuItems() {
        logger.info("Fetched all menuitems");
        return new ResponseEntity<List<MenuItems>>((List<MenuItems>) service.getAllMenuItems(), HttpStatus.OK);

    }
    /**
     * delete menu-item by name
     */

    @DeleteMapping("menuitem/{name}")
    public ResponseEntity<MenuItems>deleteMenuByMenuItem(@PathVariable("name")String name )throws MenuItemNotFoundException{
        logger.info("Menuitem is deleted");
        return new ResponseEntity<MenuItems>(service.deleteMenuItems(name),HttpStatus.OK);
    }
    /**
     * update  menu items
     */

    @PutMapping("menuitems")
    public ResponseEntity<MenuItems> updateMenuItems(@Valid @RequestBody MenuItems menuItems) throws  MenuItemNotFoundException{
        logger.info("Menu updated");
        return new ResponseEntity<MenuItems>(service.updateMenuItems(menuItems),HttpStatus.OK);
    }
    /**
     * retrieve food by name
     */

    @GetMapping("menuitems/{name}")
    public ResponseEntity<MenuItems> getMenuByName(@PathVariable String name)throws MenuItemNotFoundException{
        logger.info("Fetched menuitem by name");
        return new ResponseEntity<MenuItems>(service.findByMenuName(name),HttpStatus.FOUND);
    }

}
