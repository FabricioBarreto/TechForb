package com.techforb.Techforb.service;

import com.techforb.Techforb.dto.request.TransactionRequest;
import com.techforb.Techforb.dto.response.CardResponse;
import com.techforb.Techforb.dto.response.TransactionResponse;
import com.techforb.Techforb.models.Card;
import com.techforb.Techforb.models.User;

import java.util.List;

public interface CardService {

    Card createCard(User owner);

    CardResponse getCardByNumber(String cardNumber);

    List<Card> getAllCardsByUserId(String userEmail);

    CardResponse getCard(String userEmail);

    TransactionResponse makeTransaction(TransactionRequest request);

    boolean isCardActive(String cardNumber);

    void changeCardStatus(String cardNumber, boolean status);

}
