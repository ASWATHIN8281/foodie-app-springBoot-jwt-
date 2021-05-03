package com.stackroute.RestaurantService.repository;
import com.stackroute.RestaurantService.model.MenuItems;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemsRepository extends MongoRepository<MenuItems, Integer> {

    MenuItems findByName(String menuName);
    @Query(value = "{'name':$0}",delete = true)
    MenuItems deleteByname(String name);
    boolean existsByName(String name);
}
