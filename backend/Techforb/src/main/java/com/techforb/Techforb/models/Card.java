package com.techforb.Techforb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column(unique=true)
    private String cardNumber;

    private Double amount;

    private Date dateOfExp;

    private Boolean status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

}
