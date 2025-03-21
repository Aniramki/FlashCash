package com.Aniramki.FlashCash.service;

import com.Aniramki.FlashCash.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import com.Aniramki.FlashCash.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("SessionService")
public class SessionService {
    public SessionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private  final UserRepository userRepository;

    public User sessionUser() {
       UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userRepository.findUserByEmail(userDetails.getUsername()).orElseThrow();
    }
}
