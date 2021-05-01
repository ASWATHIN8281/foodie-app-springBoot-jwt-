package com.stackroute.RestaurantService.repository;
import com.stackroute.RestaurantService.model.MenuItems;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemsRepository extends MongoRepository<MenuItems, Integer> {
    MenuItems findByMenuId(int id);
    MenuItems findByMenuName(String itemName);
}
