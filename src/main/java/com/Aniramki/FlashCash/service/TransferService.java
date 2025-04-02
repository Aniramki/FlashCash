package com.Aniramki.FlashCash.service;

import com.Aniramki.FlashCash.model.*;
import com.Aniramki.FlashCash.repository.TransferRepository;
import com.Aniramki.FlashCash.repository.UserAccountRepository;
import com.Aniramki.FlashCash.repository.UserRepository;
import com.Aniramki.FlashCash.service.form.AddToFlashCashForm;
import com.Aniramki.FlashCash.service.form.SignUpForm;
import com.Aniramki.FlashCash.service.form.TransferForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Aniramki.FlashCash.model.Transfer;
import com.Aniramki.FlashCash.service.form.AddToFlashCashForm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("TransferService")
public class TransferService {


    private final SessionService sessionService;
    private final UserAccountRepository userAccountRepository;
    private final UserService userService;
    private final TransferRepository transferRepository;


    public TransferService(UserService userService,TransferRepository transferRepository, UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder, SessionService sessionService) {
        this.userService = userService;
        this.userAccountRepository = userAccountRepository;
        this.sessionService = sessionService;
        this.transferRepository = transferRepository;


    }
    public List<Transfer> findTransactions() {

        List<Transfer> transfers = transferRepository.findTransferByUser(sessionService.sessionUser().getId());

        return transfers;
    }

    public Transfer makeTransfer(TransferForm transferForm) {
        User from = sessionService.sessionUser();
        User to = userService.findUserByEmail(transferForm.getFriendEmail())
                .orElseThrow(() -> new IllegalArgumentException("Friend not found with email: " + transferForm.getFriendEmail()));        Transfer transfer = new Transfer();
        double amountBeforeFee = transferForm.getAmountAfterFee()+transferForm.getAmountAfterFee()*0.05;
                UserAccount userAccount = from.getAccount();

        double sold = userAccount.getAmount();
        if (sold<amountBeforeFee) {
            throw new IllegalArgumentException("Insufficient balance. Required: " + amountBeforeFee + ", Available: " + sold);        }else{
                transfer.setTo(to);
        transfer.setFrom(from);
        transfer.setAmountAfterFee(transferForm.getAmountAfterFee());
        transfer.setAmountBeforeFee(amountBeforeFee);
        transfer.setDate(new Date());

        userAccount.setAmount(sold - amountBeforeFee);
            UserAccount userAccountTo = to.getAccount();
            double soldTo = userAccountTo.getAmount();
            userAccountTo.setAmount(transferForm.getAmountAfterFee()+soldTo);
            userAccountRepository.save(userAccountTo);
            userAccountRepository.save(userAccount);}

            return   transferRepository.save(transfer);

        }

}
