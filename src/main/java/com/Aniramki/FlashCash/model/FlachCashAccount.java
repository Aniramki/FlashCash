package com.Aniramki.FlashCash.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity

public class FlachCashAccount {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
   private Double totalAmount;
    private Double flashCashAmount;

}
