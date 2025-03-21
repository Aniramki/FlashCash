package com.Aniramki.FlashCash.controller;

import com.Aniramki.FlashCash.model.UserAccount;
import com.Aniramki.FlashCash.service.form.AddContactForm;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import com.Aniramki.FlashCash.model.User;
import com.Aniramki.FlashCash.service.LinkService;
import com.Aniramki.FlashCash.service.SessionService;
import com.Aniramki.FlashCash.service.TransferService;
import com.Aniramki.FlashCash.service.UserService;
import com.Aniramki.FlashCash.service.form.AddIbanForm;
import com.Aniramki.FlashCash.service.form.SignInForm;
import com.Aniramki.FlashCash.service.form.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.Aniramki.FlashCash.model.Transfer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
//@RequestMapping("user")
public class UserController {

  private final LinkService linkService;
  private final UserService userService;
  private final TransferService transferService;
  private final SessionService sessionService;

    public UserController(LinkService linkService, UserService userService, TransferService transferService, SessionService sessionService) {
        this.linkService = linkService;
        this.userService = userService;
        this.transferService = transferService;
        this.sessionService = sessionService;
    }

    @GetMapping("/")
    public ModelAndView home(Model model) {
//        User user = sessionService.sessionUser();
//        model.addAttribute("user", user);
//        List<Transfer> transactions = transferService.findTransactions();
//        model.addAttribute("transfers", transactions);
        return new ModelAndView("home");
    }

    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
       return new ModelAndView("signup", "signUpForm", new SignUpForm());

    }


    @PostMapping("/signup")
    public ModelAndView processRequest(@ModelAttribute("signUpForm") SignUpForm form) {
        userService.registration(form);
       return new ModelAndView("signin");

    }

    @GetMapping("/add-iban")
    public ModelAndView showAddIbanForm() {
        return new ModelAndView("add-iban", "addIbanForm", new AddIbanForm());

    }


    @PostMapping("/add-iban")

    public String addIban(@ModelAttribute("addIbanForm") AddIbanForm ibanForm,
                          @AuthenticationPrincipal UserDetails userDetails) {
        userService.addIbanToUser(ibanForm, userDetails.getUsername());
        return "redirect:/profile";
    }

    @GetMapping("/add-friend")
    public ModelAndView showAddIbanForm() {
        return new ModelAndView("add-iban", "addIbanForm", new AddIbanForm());

    }


    @PostMapping("/add-friend")

    public String addIban(@ModelAttribute("addContactForm") AddContactForm contactForm,
                          @AuthenticationPrincipal UserDetails userDetails) {
        userService.addContactToUser(contactForm, userDetails.getUsername());
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public ModelAndView profile(Model model) {
        User user = sessionService.sessionUser();
        UserAccount userAccount = user.getAccount();
       model.addAttribute("user", user);
        model.addAttribute("userAccount", userAccount);

        List<Transfer> transactions = transferService.findTransactions();
        model.addAttribute("transfers", transactions);

                return new ModelAndView("profile");

    }




//
//    @GetMapping("profile")
//    public ModelAndView profile(Model model) {
//        User user = sessionService.sessionUser();
//        model.addAttribute("user", user);
//        return new ModelAndView("profile");
//    }
//
//    @GetMapping("add-iban")
//    public ModelAndView getAddConnectionForm(Model model) {
//        return new ModelAndView("add-iban", "addIbanForm", new AddIbanForm()) ;
//    }
//
//    @PostMapping("add-iban")
//    public ModelAndView addIban(Model model, @ModelAttribute("addIbanForm") AddIbanForm form) {
//        return new ModelAndView("add-iban", "message", "IBAN a ete ajouté!");
//    }




}
