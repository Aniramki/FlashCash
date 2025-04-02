package com.Aniramki.FlashCash.service.form;

import com.Aniramki.FlashCash.model.User;

import lombok.Data;

@Data
public class TransferForm {

    private User to;
    private Double amountAfterFee;
    private String friendEmail;
}
