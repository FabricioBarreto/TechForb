package com.Techforb.Techforb.repository;

import com.Techforb.Techforb.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {
}
