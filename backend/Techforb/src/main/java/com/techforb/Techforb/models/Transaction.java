package com.techforb.Techforb.models;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private Card cardSender;

    private Card cardReceiver;

    private Date dateOfTransaction;

    public void CreateTransaction(Card cardSender, Card cardReceiver){
        this.cardSender = cardSender;
        this.cardReceiver = cardReceiver;
        Transaction.builder().build().cardSender(cardSender)
        cardSender.getTransactions().add(Transaction.builder().build());
    }
}
