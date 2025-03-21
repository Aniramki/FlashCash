package com.Aniramki.FlashCash.repository;

import com.Aniramki.FlashCash.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {



   // @Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.links WHERE u.email=:email ")
    public Optional<User> findUserByEmail(String email);


}