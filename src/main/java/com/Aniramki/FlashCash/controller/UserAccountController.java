package com.Aniramki.FlashCash.controller;

import com.Aniramki.FlashCash.model.Transfer;
import com.Aniramki.FlashCash.model.User;
import com.Aniramki.FlashCash.model.UserAccount;
import com.Aniramki.FlashCash.service.*;
import com.Aniramki.FlashCash.service.form.AddContactForm;
import com.Aniramki.FlashCash.service.form.AddIbanForm;
import com.Aniramki.FlashCash.service.form.AddToFlashCashForm;
import com.Aniramki.FlashCash.service.form.SignUpForm;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
//@RequestMapping("user")
public class UserAccountController {

  private final LinkService linkService;
  private final UserService userService;
  private final UserAccountService userAccountService;
  private final SessionService sessionService;
    private final TransferService transferService;


    public UserAccountController(LinkService linkService, TransferService transferService, UserService userService, UserAccountService userAccountService, SessionService sessionService) {
        this.linkService = linkService;
        this.userService = userService;
        this.userAccountService = userAccountService;
        this.sessionService = sessionService;
        this.transferService = transferService;
    }


    @GetMapping("/add-to-flashcash")
    public ModelAndView AddMoneyToFlashCashForm() {
        return new ModelAndView("add-to-flashcash", "addToFlashCashForm", new AddToFlashCashForm());

    }

    @PostMapping("/add-to-flashcash")

    public String AddMoneyToFlashCash(@ModelAttribute("addToFlashCashForm") AddToFlashCashForm addToFlashCashForm,
                          @AuthenticationPrincipal UserDetails userDetails) {
     userAccountService.AddMoneyToFlashCash(addToFlashCashForm);
        return "redirect:/profile";
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
