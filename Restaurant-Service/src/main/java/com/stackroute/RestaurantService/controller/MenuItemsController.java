package com.stackroute.RestaurantService.controller;

import com.stackroute.RestaurantService.exception.MenuItemAlreadyExistsException;
import com.stackroute.RestaurantService.exception.MenuItemNotFoundException;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.service.MenuItemDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class MenuItemsController {
    @Autowired
    private MenuItemDAO service;
    @PostMapping("menuitem")
    public ResponseEntity<MenuItems> saveNewMenu(@RequestBody MenuItems menuItems) throws MenuItemAlreadyExistsException {
        return new ResponseEntity<MenuItems>(service.addMenuItems(menuItems), HttpStatus.CREATED);
    }
    @GetMapping("menuitems")
    public ResponseEntity<List<MenuItems>> getAllMenuItems() {
        return new ResponseEntity<List<MenuItems>>((List<MenuItems>) service.getAllMenuItems(), HttpStatus.OK);

    }
    @DeleteMapping("menuitem/{name}")
    public ResponseEntity<MenuItems>deleteMenuByMenuItem(@PathVariable("name")String name )throws MenuItemNotFoundException{
        return new ResponseEntity<MenuItems>(service.deleteMenuItems(name),HttpStatus.OK);
    }

    @PutMapping("menuitems")
    public ResponseEntity<MenuItems> updateMenuItems(@RequestBody MenuItems menuItems) throws  MenuItemNotFoundException{
        return new ResponseEntity<MenuItems>(service.updateMenuItems(menuItems),HttpStatus.OK);
    }

    @GetMapping("menuitems/{name}")
    public ResponseEntity<MenuItems> getMenuByName(@PathVariable String name)throws MenuItemNotFoundException{
        return new ResponseEntity<MenuItems>(service.findByMenuName(name),HttpStatus.FOUND);
    }

}
