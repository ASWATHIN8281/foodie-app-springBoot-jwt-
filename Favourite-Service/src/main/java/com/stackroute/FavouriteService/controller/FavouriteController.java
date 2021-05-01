package com.stackroute.FavouriteService.controller;

import com.stackroute.FavouriteService.model.Favourite;
import com.stackroute.FavouriteService.service.Dao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class FavouriteController {
    @Autowired
    private Dao dao;

    @PostMapping("/favourite")
    public ResponseEntity<Favourite> addFavourite(@RequestBody Favourite favourite){
        return new ResponseEntity<Favourite>(dao.addFavourite(favourite), HttpStatus.CREATED);

    }
    @GetMapping("/favourites")
    public List<Favourite> getAll(){
        return dao.getAll();
    }


    @DeleteMapping("/favourite/{foodItem}")
    public void deleteFavourite(@PathVariable String foodItem){
        dao.deleteFavourite(foodItem);
}

}
