package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.repository.MenuItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class MenuItemDAOImpl implements MenuItemDAO{
    @Autowired
    private MenuItemsRepository repository;


    @Override
    public MenuItems addNewItem(MenuItems menuItems) {

        return repository.save(menuItems);
    }

    @Override
    public List<MenuItems> getAllItemsInMenu() {

        return  repository.findAll();
    }

    @Override
    public MenuItems deleteItemInMenu(int id) {
        MenuItems menuItems=null;
        Optional optional=Optional.of(repository.findByMenuId(id));
        if(optional.isPresent()){
            menuItems=repository.findByMenuId(id);
            repository.deleteById(id);
        }
        return menuItems;
    }

    @Override
    public MenuItems updateMenuItems(MenuItems menuItems) {

        MenuItems updatedMenuItems=null;
        Optional optional=Optional.of(repository.findByMenuId(menuItems.getId()));
        if(optional.isPresent()){
            MenuItems getMenuItem=repository.findByMenuId(menuItems.getId());
            getMenuItem.setDescription(menuItems.getDescription());
            getMenuItem.setName(menuItems.getName());
            getMenuItem.setPrice(menuItems.getPrice());
            updatedMenuItems=addNewItem(getMenuItem);
        }

        return updatedMenuItems;

    }

    @Override
    public MenuItems findByMenuId(int id) {

        return  repository.findByMenuId(id);
    }

    @Override
    public MenuItems findByMenuName(String itemName) {

        return repository.findByMenuName(itemName);
    }
}
