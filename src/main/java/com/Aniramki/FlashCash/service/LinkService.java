package com.Aniramki.FlashCash.service;


import com.Aniramki.FlashCash.model.Link;
import com.Aniramki.FlashCash.model.User;
import com.Aniramki.FlashCash.model.UserAccount;
import com.Aniramki.FlashCash.repository.LinkRepository;
import com.Aniramki.FlashCash.repository.UserAccountRepository;
import com.Aniramki.FlashCash.repository.UserRepository;
import com.Aniramki.FlashCash.service.form.AddContactForm;
import com.Aniramki.FlashCash.service.form.AddIbanForm;
import com.Aniramki.FlashCash.service.form.SignUpForm;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("LinkService")
public class LinkService {
    private final SessionService sessionService;
    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public LinkService(UserRepository userRepository, LinkRepository linkRepository, SessionService sessionService) {
        this.userRepository = userRepository;
        this.linkRepository = linkRepository;
        this.sessionService = sessionService;
    }


    public Link addContactToUser (AddContactForm contactForm, String email) {
      User user = userRepository.findUserByEmail(email)
              .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        User user3 = userRepository.findUserByEmail(contactForm.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Optional<Link> existingLink = linkRepository.findByUser1AndUser2(user, user3);
        if (existingLink.isPresent()) {
            throw new IllegalStateException("Users are already friends");
        }
        Link link = new Link();
        link.setUser1(user);
        link.setUser2(user3);
        return linkRepository.save(link);
    }

    public List<User> getFriends(User user) {

        List<Link> links = linkRepository.findByUser1(user);

        List<User> friends = new ArrayList<>();
        for (Link link : links) {
            if (!link.getUser1().equals(user)) {
                friends.add(link.getUser1());
            }
            if (!link.getUser2().equals(user)) {
                friends.add(link.getUser2());
            }
        }
        return friends;
    }

}
