package com.Techforb.Techforb.repository;

import com.Techforb.Techforb.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,String> {

    Optional<Card> findByCardNumber(String cardNumber);

    @Query("SELECT c FROM Card c WHERE c.owner.id = :userId")
    List<Card> getAllCardsByUserId(Long userId);


}
