package com.stackroute.FavouriteService.repository;

import com.stackroute.FavouriteService.model.Favourite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends CrudRepository<Favourite,String> {
}
