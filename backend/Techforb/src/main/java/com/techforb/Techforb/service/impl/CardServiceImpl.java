package com.techforb.Techforb.service.impl;

import com.techforb.Techforb.dto.request.TransactionRequest;
import com.techforb.Techforb.dto.response.CardResponse;
import com.techforb.Techforb.dto.response.TransactionResponse;
import com.techforb.Techforb.exceptions.CardException;
import com.techforb.Techforb.exceptions.EmailException;
import com.techforb.Techforb.mapper.CardMapper;
import com.techforb.Techforb.models.Card;
import com.techforb.Techforb.models.User;
import com.techforb.Techforb.repository.CardRepository;
import com.techforb.Techforb.repository.UserRepository;
import com.techforb.Techforb.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardMapper cardMapper;

    @Override
    public Card createCard(User owner) {
        Card newCard = new Card();
        newCard.setCardNumber(createNumber());
        newCard.setAmount(0.0);
        newCard.setDateOfExp(generateDateOfExp());
        newCard.setStatus(true);
        newCard.setOwner(owner);
        return cardRepository.save(newCard);
    }

    @Override
    public CardResponse getCardByNumber(String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(()-> new CardException("Card %s not found", cardNumber));
        return cardMapper.cardToCardResponse(card);
    }

    @Override
    public List<Card> getAllCardsByUserId(String userEmail) {

        User user = userRepository.findByEmail(userEmail).orElseThrow(()-> new EmailException("User with with this email do not exist"));

        return cardRepository.getAllCardsByUserId(user.getId());
    }

    @Override
    public CardResponse getCard(String userEmail){
        User user = userRepository.findByEmail(userEmail).orElseThrow(()-> new EmailException("User with with this email do not exist"));

        List<Card> cards = cardRepository.getAllCardsByUserId(user.getId());

        Card card = cards.get(0);

        User owner = card.getOwner();

        CardResponse response =  cardMapper.cardToCardResponse(card);

        response.setOwnerName(owner.getFullname());

        return response;
    }

    @Override
    public TransactionResponse makeTransaction(TransactionRequest request) {

        // Buscar la tarjeta del sender por número de tarjeta
        Card senderCard = cardRepository.findByCardNumber(request.getSenderCard())
                .orElseThrow(() -> new CardException("Card %s not found", request.getSenderCard()));

        // Buscar la tarjeta del receiver por número de tarjeta
        Card cardReceiver = cardRepository.findByCardNumber(request.getReceiverCard())
                .orElseThrow(() -> new CardException("Card %s not found", request.getReceiverCard()));

        // Verificar que el sender posea suficiente saldo para la transacción
        if (senderCard.getAmount() < request.getAmount()) {
            throw new CardException("Insufficient balance in card %s", request.getSenderCard());
        }

        // Verificar que la cuenta del receiver esté activa
        if (!cardReceiver.getStatus()) {
            throw new CardException("Card %s is not active.", request.getReceiverCard());
        }

        // Realizar la transacción
        senderCard.setAmount(senderCard.getAmount() - request.getAmount());
        cardReceiver.setAmount(cardReceiver.getAmount() + request.getAmount());

        // Actualizar ambas tarjetas en la base de datos
        cardRepository.save(senderCard);
        cardRepository.save(cardReceiver);

        return new TransactionResponse();
    }

    @Override
    public boolean isCardActive(String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(()-> new CardException("Card %s not found", cardNumber));
        return card.getStatus();
    }

    @Override
    public void changeCardStatus(String cardNumber, boolean status) {
        Card card = cardRepository.findByCardNumber(cardNumber).orElseThrow(()-> new CardException("Card %s not found", cardNumber));
        card.setStatus(status);
    }

    public String createNumber(){
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10); // Genera un dígito aleatorio del 0 al 9
            cardNumber.append(digit);
        }
        return cardNumber.toString();
    }

    public Date generateDateOfExp(){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date();

        // Suma dos años a la fecha actual
        calendar.setTime(currentDate);
        calendar.add(Calendar.YEAR, 2);

        return calendar.getTime();
    }

}
