package com.Aniramki.FlashCash.repository;

import com.Aniramki.FlashCash.model.Link;
import com.Aniramki.FlashCash.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {


        Optional<Link> findByUser1AndUser2(User user1, User user2);
    List<Link> findByUser1(User user1);


}