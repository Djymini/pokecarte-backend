package com.loutredev.pokecarte.persistences.repositories;

import com.loutredev.pokecarte.persistences.entities.ProductEntity;
import com.loutredev.pokecarte.persistences.entities.SetCardEntitty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetCardRepository extends JpaRepository<SetCardEntitty, String> {
    List<SetCardEntitty> findByNameOrderByReleaseDateDesc(String keyword);
}
