package com.Techforb.Techforb.service;

import com.Techforb.Techforb.dto.request.TransactionRequest;
import com.Techforb.Techforb.dto.response.CardResponse;
import com.Techforb.Techforb.dto.response.TransactionResponse;
import com.Techforb.Techforb.models.Card;
import com.Techforb.Techforb.models.User;

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
