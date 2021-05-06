package com.stackroute.FavouriteService.controller;

import com.stackroute.FavouriteService.Exception.FavouriteListNotFoundException;
import com.stackroute.FavouriteService.Exception.FoodItemAlreadyExistsException;
import com.stackroute.FavouriteService.Exception.FoodItemNotFoundException;
import com.stackroute.FavouriteService.Exception.UsernameNotFoundException;
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

import javax.validation.Valid;
import java.util.List;
/**
 * @Slf4j is used to implement logging
 * @RestController is used to declare the class as restcontroller layer
 * @AllArgsConstructor generates a constructor with 1 parameter for each field
 * @RequestMapping  maps HTTP requests to handler methods of  restcontroller
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class FavouriteController {

    private static Logger logger = (Logger) LoggerFactory.getLogger(FavouriteController.class);
//autowires the favouriteDao
    @Autowired
    private FavouriteDao favouriteDao;

    /**
     *
     * @param favourite
     * The method used to add Favourites of the customer to Favourite
     * @Valid is used for validation
     *  @PostMapping is used to handle POST type of request method
     *  The logger will log the required info
     * @throws FoodItemAlreadyExistsException if the given fooditem already exists in customers favouritelist
     */
    @PostMapping("/favourite")
    public ResponseEntity<Favourite> addFavourite( @Valid @RequestBody Favourite favourite) throws FoodItemAlreadyExistsException {

            if (favourite==null){
            logger.error("There is a error.");

            return new ResponseEntity<Favourite>(HttpStatus.BAD_REQUEST);
        } else {

            logger.info("The favourite foodItem is added successfully.");
            return new ResponseEntity<Favourite>(favouriteDao.addFavourite (favourite), HttpStatus.CREATED);

        }
    }

    /**
     * this method returns the list of favourites of all customers
     * The logger will log the required info

     */
    @GetMapping("/favourites")
    public ResponseEntity<List<Favourite>> getAll() {


            logger.info("The Favourite List is Successfully found.");

        return new ResponseEntity<>( favouriteDao.getAll(), HttpStatus.OK);
    }

    /**
     *
     * @param username
     * This method gets the favourites of customer based on username
     * The logger will log the required info
     * @throws UsernameNotFoundException if the username is not found in the database
     */
    @GetMapping("/favourite/{username}")
    public ResponseEntity<List<Favourite>>getFavouriteByUsername(@PathVariable String username)
            throws UsernameNotFoundException {
        logger.info("This is the list of favourites of",username);
        return new ResponseEntity<>(( favouriteDao.getFavouriteByUsername(username)),HttpStatus.FOUND);
    }
    /**
     * @param username
     * @param foodItem
     * The method deletes the favourite of customer using the username and fooditem
     *  returns the deleted favourite
     *  The logger will log the required info
     * @throws FoodItemNotFoundException if the given fooditem is not found as favourite in the favouritelist
     */
    @DeleteMapping("/favourite")
    public ResponseEntity<Favourite> deleteFavourite( @RequestParam(value ="username") String username,
                                                      @RequestParam (value = "fooditem") String foodItem)
            throws FavouriteListNotFoundException {


            logger.info("The favourite foodItem is deleted successfully.");
            return new ResponseEntity<Favourite>(favouriteDao.deleteFavourite(username,foodItem), HttpStatus.OK);


    }

}
