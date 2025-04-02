package com.Aniramki.FlashCash.service;

import com.Aniramki.FlashCash.model.User;
import com.Aniramki.FlashCash.model.UserAccount;
import com.Aniramki.FlashCash.repository.UserAccountRepository;
import com.Aniramki.FlashCash.repository.UserRepository;
import com.Aniramki.FlashCash.service.form.AddContactForm;
import com.Aniramki.FlashCash.service.form.AddIbanForm;
import com.Aniramki.FlashCash.service.form.SignInForm;
import com.Aniramki.FlashCash.service.form.SignUpForm;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("UserService")

public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;
    private final UserAccountRepository userAccountRepository;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder, SessionService sessionService) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;

        this.passwordEncoder = passwordEncoder;
        this.sessionService = sessionService;
    }



    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    public User registration(SignUpForm form) {
        if (findUserByEmail(form.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists.");
        }
        else {
                User user = new User();
                user.setEmail(form.getEmail());
                user.setFirstName(form.getFirstName());
                user.setLastName(form.getLastName());
            UserAccount account = new UserAccount();
                account.setAmount(0.0);
                user.setAccount(account);
            user.setPassword(passwordEncoder.encode(form.getPassword()));

          return   userRepository.save(user);

        }


    }

    public UserAccount addIbanToUser(AddIbanForm ibanForm, String email) {
            User user = userRepository.findUserByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
       UserAccount userAccount = user.getAccount();
               userAccount.setIban(ibanForm.getIban());
       return userAccountRepository.save(userAccount);
    }



//    public Optional<User> passwordCompare(SignInForm form) {
//        Optional<User> optionalUser = userRepository.findUserByEmail(form.getEmail());
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            String hashedPassword = user.getPassword();
//
//            if (passwordEncoder.matches(form.getPassword(), hashedPassword)) {
//                return optionalUser; // Return the user if the password matches
//            } else {
//                throw new RuntimeException("Incorrect password.");
//            }
//        } else {
//            throw new RuntimeException("User with this email does not exist.");
//        }
//    }

}