package com.Aniramki.FlashCash.service;


import com.Aniramki.FlashCash.model.Link;
import com.Aniramki.FlashCash.model.User;
import com.Aniramki.FlashCash.repository.UserAccountRepository;
import com.Aniramki.FlashCash.repository.UserRepository;
import com.Aniramki.FlashCash.service.form.AddContactForm;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("LinkService")
public class LinkService {
    private final SessionService sessionService;
    private final UserAccountRepository userAccountRepository;
    private final UserRepository userRepository;

    public LinkService(UserRepository userRepository, UserAccountRepository userAccountRepository, SessionService sessionService) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
        this.sessionService = sessionService;
    }


//    public Link addContactToUser (AddContactForm contactForm, String email) {
//      Optional<User> user = userRepository.findUserByEmail(email);
//       // Link Friend = (Link) user;
//        return
//  }
}
