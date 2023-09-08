package com.Techforb.Techforb.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class CardResponse {

    private String number;

    private Double amount;

    private Date dateOfExp;

    private String ownerName;

}
