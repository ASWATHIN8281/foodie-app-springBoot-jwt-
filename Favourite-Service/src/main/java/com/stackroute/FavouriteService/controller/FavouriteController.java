package com.stackroute.FavouriteService.controller;

import com.stackroute.FavouriteService.Exception.FoodItemAlreadyExistsException;
import com.stackroute.FavouriteService.Exception.FoodItemNotFoundException;
import com.stackroute.FavouriteService.model.Favourite;
import com.stackroute.FavouriteService.service.FavouriteDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class FavouriteController {

    private static Logger logger = (Logger) LoggerFactory.getLogger(FavouriteController.class);

    @Autowired
    private FavouriteDao favouriteDao;

    @PostMapping("/favourite")
    public ResponseEntity<Favourite> addFavourite(@RequestBody Favourite favourite) throws FoodItemAlreadyExistsException {

            if (favourite==null){
            logger.error("There is a error.");

            return new ResponseEntity<Favourite>(HttpStatus.BAD_REQUEST);
        } else {

            logger.info("The favourite foodItem is added successfully.");
            return new ResponseEntity<Favourite>(favouriteDao.addFavourite (favourite), HttpStatus.CREATED);

        }
    }

    @GetMapping("/favourites")
    public ResponseEntity<List<Favourite>> getAll() {


            logger.info("The Favourite List is Successfully found.");

        return new ResponseEntity<>( favouriteDao.getAll(), HttpStatus.OK);
    }
    @GetMapping("/favourite/{username}")
    public ResponseEntity<List<Favourite>>getFavouriteByUsername(@PathVariable String username){
        return new ResponseEntity<>(( favouriteDao.getFavouriteByUsername(username)),HttpStatus.FOUND);
    }

    @DeleteMapping("/favourite/{foodItem}")
    public ResponseEntity<Favourite> deleteFavourite( @PathVariable String foodItem)
            throws FoodItemNotFoundException {


            logger.info("The favourite foodItem is deleted successfully.");
            return new ResponseEntity<Favourite>(favouriteDao.deleteFavourite(foodItem), HttpStatus.OK);


    }

}
