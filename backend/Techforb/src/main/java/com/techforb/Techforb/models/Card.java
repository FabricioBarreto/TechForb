package com.Techforb.Techforb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @Temporal(TemporalType.DATE)
    private Date dateOfExp;

    private Boolean status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

}
