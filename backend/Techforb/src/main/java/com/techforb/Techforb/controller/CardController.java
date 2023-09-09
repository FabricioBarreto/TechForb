package com.techforb.Techforb.controller;

import com.techforb.Techforb.dto.request.TransactionRequest;
import com.techforb.Techforb.dto.response.CardResponse;
import com.techforb.Techforb.dto.response.TransactionResponse;
import com.techforb.Techforb.models.Card;
import com.techforb.Techforb.service.CardService;
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
