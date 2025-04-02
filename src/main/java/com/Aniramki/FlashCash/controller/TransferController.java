package com.Aniramki.FlashCash.controller;

import com.Aniramki.FlashCash.model.Transfer;
import com.Aniramki.FlashCash.model.User;
import com.Aniramki.FlashCash.model.UserAccount;
import com.Aniramki.FlashCash.service.*;
import com.Aniramki.FlashCash.service.form.AddContactForm;
import com.Aniramki.FlashCash.service.form.AddToFlashCashForm;
import com.Aniramki.FlashCash.service.form.TransferForm;
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
public class TransferController {

  private final LinkService linkService;
  private final UserService userService;
  private final UserAccountService userAccountService;
  private final SessionService sessionService;
    private final TransferService transferService;


    public TransferController(LinkService linkService, TransferService transferService, UserService userService, UserAccountService userAccountService, SessionService sessionService) {
        this.linkService = linkService;
        this.userService = userService;
        this.userAccountService = userAccountService;
        this.sessionService = sessionService;
        this.transferService = transferService;
    }


    @GetMapping("/transfers")
    public ModelAndView transfer(Model model) {
        User user = sessionService.sessionUser();
        UserAccount userAccount = user.getAccount();
        model.addAttribute("user", user);
        List<User> friends = linkService.getFriends(user);
        model.addAttribute("friends", friends);
        model.addAttribute("userAccount", userAccount);
        List<Transfer> transactions = transferService.findTransactions();
        model.addAttribute("transfers", transactions);

        return new ModelAndView("transfers");
    }

    @GetMapping("/transfer-to-contact")
    public ModelAndView transferToContact(Model model) {
        User user = sessionService.sessionUser();
        UserAccount userAccount = user.getAccount();
        model.addAttribute("user", user);
        List<User> friends = linkService.getFriends(user);
        model.addAttribute("friends", friends);
        model.addAttribute("userAccount", userAccount);
        List<Transfer> transactions = transferService.findTransactions();
        model.addAttribute("transfers", transactions);

        return new ModelAndView("transfer-to-contact");
    }

    @PostMapping("/transfer-to-contact")
    public String addTransfer(@ModelAttribute("transferForm") TransferForm transferForm) {
        transferService.makeTransfer(transferForm);
        return "redirect:/transfers";

    }


}
