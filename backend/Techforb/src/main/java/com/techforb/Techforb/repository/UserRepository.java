package com.techforb.Techforb.repository;

import com.techforb.Techforb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.numberDocument = :numberDocument")
    Optional<User> findByNumberDocument(@Param("numberDocument") String numberDocument);

    Boolean existsByNumberDocument(String number);

}
