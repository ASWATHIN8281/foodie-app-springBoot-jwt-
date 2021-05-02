package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.MenuItemAlreadyExistsException;
import com.stackroute.RestaurantService.exception.MenuItemNotFoundException;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.model.Restaurant;

import java.util.List;

public interface MenuItemDAO {
    MenuItems addMenuItems(MenuItems menuItems) throws MenuItemAlreadyExistsException;
    List<MenuItems> getAllMenuItems();
    MenuItems deleteMenuItems(String menuItem)throws MenuItemNotFoundException;
    MenuItems updateMenuItems(MenuItems menuItems) throws  MenuItemNotFoundException;
    MenuItems findByMenuName(String name)throws MenuItemNotFoundException;

}
