package com.stackroute.FavouriteService.service;

import com.stackroute.FavouriteService.model.Favourite;
import com.stackroute.FavouriteService.repository.FavouriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FavouriteService implements Dao {
    @Autowired
    private FavouriteRepository repository;


    @Override
    public Favourite addFavourite(Favourite favourite) {
        return repository.save(favourite);
    }

    @Override
    public void deleteFavourite(String foodItem) {
        repository.deleteById(foodItem);
    }

    @Override
    public List<Favourite> getAll(){
        return (List<Favourite>) repository.findAll();
    }
}
