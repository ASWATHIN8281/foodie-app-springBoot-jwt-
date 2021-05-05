package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.MenuItemAlreadyExistsException;
import com.stackroute.RestaurantService.exception.MenuItemNotFoundException;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.model.Restaurant;

import java.util.List;

public interface MenuItemDAO {
    /**
     * AbstractMethod to save a menu
     */

    MenuItems addMenuItems(MenuItems menuItems) throws MenuItemAlreadyExistsException;
    /**
     * AbstractMethod to get all men
     */

    List<MenuItems> getAllMenuItems();
    /**
     * AbstractMethod to delete menu
     */

    MenuItems deleteMenuItems(String menuItem)throws MenuItemNotFoundException;
    /**
     * AbstractMethod to update menuitems
     */

    MenuItems updateMenuItems(MenuItems menuItems) throws  MenuItemNotFoundException;
    /**
     * AbstractMethod to find menu by name
     */

    MenuItems findByMenuName(String name)throws MenuItemNotFoundException;

}
