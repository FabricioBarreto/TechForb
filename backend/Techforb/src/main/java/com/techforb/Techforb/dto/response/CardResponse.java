package com.techforb.Techforb.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techforb.Techforb.models.User;
import lombok.Data;

import java.util.Date;

@Data
public class CardResponse {

    private String number;

    private Double amount;

    private Date dateOfExp;

    private String ownerName;

}
