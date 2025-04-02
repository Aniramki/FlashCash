package com.Aniramki.FlashCash.controller;

import com.Aniramki.FlashCash.model.UserAccount;
import com.Aniramki.FlashCash.service.dto.BarChartForm;
import com.Aniramki.FlashCash.service.form.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import com.Aniramki.FlashCash.model.User;
import com.Aniramki.FlashCash.service.LinkService;
import com.Aniramki.FlashCash.service.SessionService;
import com.Aniramki.FlashCash.service.TransferService;
import com.Aniramki.FlashCash.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.Aniramki.FlashCash.model.Transfer;

import java.util.List;
import java.util.Map;


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
    public ModelAndView showAddFriendForm() {
        return new ModelAndView("add-friend", "addFriendForm", new AddContactForm());

    }


    @PostMapping("/add-friend")

    public String addIban(@ModelAttribute("addContactForm") AddContactForm contactForm,
                          @AuthenticationPrincipal UserDetails userDetails) {
        linkService.addContactToUser(contactForm, userDetails.getUsername());
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public ModelAndView profile(Model model) {
        User user = sessionService.sessionUser();
        UserAccount userAccount = user.getAccount();
        model.addAttribute("user", user);
        List<User> friends = linkService.getFriends(user);
        model.addAttribute("friends", friends);
        model.addAttribute("userAccount", userAccount);
        List<Transfer> transactions = transferService.findTransactions();
        model.addAttribute("transfers", transactions);

                return new ModelAndView("profile");



    }




//    @GetMapping("/friendAmountMonth")
//    public ModelAndView showBarChartForm() {
//        return new ModelAndView("friendAmountMonth", "BarChartForm", new BarChartForm());
//
//    }
//
//
//    @PostMapping("/friendAmountMonth")
//    public List<Map<String, Object>> getFriendAmountMonth(
//            @RequestParam int userId,
//            @RequestParam(required = false) Integer year) {
//
//        return jdbcTemplate.queryForList("CALL spFriendAmountMonth(?, ?)", userId, year);
//    }



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
