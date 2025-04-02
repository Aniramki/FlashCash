package com.Aniramki.FlashCash.service;

import com.Aniramki.FlashCash.model.Transfer;
import com.Aniramki.FlashCash.model.User;
import com.Aniramki.FlashCash.model.UserAccount;
import com.Aniramki.FlashCash.repository.UserAccountRepository;
import com.Aniramki.FlashCash.repository.UserRepository;
import com.Aniramki.FlashCash.service.form.AddToFlashCashForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserAccountService")
public class UserAccountService {
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;
    private final UserAccountRepository userAccountRepository;
    private final UserRepository userRepository;

    public UserAccountService(UserRepository userRepository, UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder, SessionService sessionService) {
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;

        this.passwordEncoder = passwordEncoder;
        this.sessionService = sessionService;
    }

    public UserAccount AddMoneyToFlashCash(AddToFlashCashForm addToFlashCashForm) {
        User user = sessionService.sessionUser();
        UserAccount userAccount = user.getAccount();
        double sold = userAccount.getAmount();
        userAccount.setAmount(addToFlashCashForm.getAmount()+ sold);
        return userAccountRepository.save(userAccount);
    }
}
