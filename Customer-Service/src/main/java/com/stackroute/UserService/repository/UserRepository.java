package com.stackroute.UserService.repository;

import com.stackroute.UserService.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    //find customer by customer username

    User findByUsername(String username);

    //exist customer by customer username

    boolean existsByusername(String username);
}
