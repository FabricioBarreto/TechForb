package com.Techforb.Techforb.mapper;

import com.Techforb.Techforb.dto.response.CardResponse;
import com.Techforb.Techforb.models.Card;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    @Autowired
    private ModelMapper mapper;

    public CardResponse cardToCardResponse(Card card){
        return mapper.map(card, CardResponse.class);
    }


}
