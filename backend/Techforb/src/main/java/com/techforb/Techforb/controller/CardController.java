package com.Techforb.Techforb.controller;

import com.Techforb.Techforb.dto.request.TransactionRequest;
import com.Techforb.Techforb.dto.response.CardResponse;
import com.Techforb.Techforb.dto.response.TransactionResponse;
import com.Techforb.Techforb.models.Card;
import com.Techforb.Techforb.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/cards/{userEmail}")
    private List<Card> getAllCards(@PathVariable String userEmail){
        return cardService.getAllCardsByUserId(userEmail);
    }

    @GetMapping("/card/{userEmail}")
    private CardResponse getCard(@PathVariable String userEmail){
        return cardService.getCard(userEmail);
    }

    @PostMapping("/transaction")
    private TransactionResponse makeTransaction(@Valid @RequestBody TransactionRequest request){
        return cardService.makeTransaction(request);
    }

}
