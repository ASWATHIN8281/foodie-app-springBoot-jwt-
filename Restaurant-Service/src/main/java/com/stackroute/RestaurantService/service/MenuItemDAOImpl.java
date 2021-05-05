package com.stackroute.RestaurantService.service;

import com.stackroute.RestaurantService.exception.MenuItemAlreadyExistsException;
import com.stackroute.RestaurantService.exception.MenuItemNotFoundException;
import com.stackroute.RestaurantService.model.MenuItems;
import com.stackroute.RestaurantService.repository.MenuItemsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@AllArgsConstructor
@Service
public class MenuItemDAOImpl implements MenuItemDAO{
    @Autowired
    private MenuItemsRepository repository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(MenuItemDAOImpl.class);

    @Override
    public MenuItems addMenuItems(MenuItems menuItems) throws MenuItemAlreadyExistsException {
        if (repository.existsById(menuItems.getMenuId())) {
            logger.error("Menu items already exists");
            throw new MenuItemAlreadyExistsException();
        }
        MenuItems menuItems1=repository.save(menuItems);
        return menuItems1;
    }
    @Override
    public List<MenuItems> getAllMenuItems() {

        return  repository.findAll();
    }

    @Override
    public MenuItems deleteMenuItems(String  menuItem) throws MenuItemNotFoundException{
        MenuItems menuItems=null;
       // Optional optional=Optional.of(repository.findByName(menuItem));
        if(!repository.existsByName(menuItem)){
            logger.error("Menu item not found");
           throw new MenuItemNotFoundException();
        }
        menuItems=repository.findByName(menuItem);
        repository.deleteByname(menuItem);
        logger.info("Menu item deleted");
        return menuItems;
    }

    @Override
    public MenuItems updateMenuItems(MenuItems menuItems)throws MenuItemNotFoundException {

        MenuItems updatedMenuItems=null;
       // Optional optional=repository.findById(menuItems.getMenuId());
        if(!repository.existsByName(menuItems.getName())){
            logger.error("Menu item not found");
            throw new MenuItemNotFoundException();
        }
       // MenuItems getMenuItem=new MenuItems(menuItems.getMenuId(),menuItems.getCategory(),menuItems.getName(),
        //        menuItems.getDescription(),menuItems.getPrice());
        updatedMenuItems=repository.save(menuItems);
        logger.info("Menu item updated");
        return updatedMenuItems;

    }


    @Override
    public MenuItems findByMenuName(String itemName) throws MenuItemNotFoundException{
      //  Optional optional=Optional.of(repository.findByName(itemName));
        if(!repository.existsByName(itemName)){
            throw  new MenuItemNotFoundException();
        }
        MenuItems menuItems=repository.findByName(itemName);
        return  menuItems;
    }
}
