package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.MenuItemAlreadyExistsException;
import com.stackroute.RestaurantService.exception.MenuItemNotFoundException;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.model.Restaurant;

import java.util.List;

public interface MenuItemDAO {
    MenuItems addNewItem(MenuItems menuItems) throws MenuItemAlreadyExistsException;
    List<MenuItems> getAllItemsInMenu();
    MenuItems deleteItemInMenu(int id)throws MenuItemNotFoundException;
    MenuItems updateMenuItems(MenuItems menuItems) throws  MenuItemNotFoundException;
    MenuItems findByMenuId(int id)throws MenuItemNotFoundException;
    MenuItems findByMenuName(String itemName)throws MenuItemNotFoundException;
}
