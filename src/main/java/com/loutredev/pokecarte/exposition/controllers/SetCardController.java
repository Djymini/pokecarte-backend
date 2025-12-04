package com.loutredev.pokecarte.exposition.controllers;

import com.loutredev.pokecarte.domain.services.SetCardService;
import com.loutredev.pokecarte.exposition.dtos.ProductResponseDTO;
import com.loutredev.pokecarte.exposition.dtos.SetCardResponseDTO;
import com.loutredev.pokecarte.mappers.ProductMapper;
import com.loutredev.pokecarte.mappers.SetCardMapper;
import com.loutredev.pokecarte.persistences.entities.ProductEntity;
import com.loutredev.pokecarte.persistences.entities.SetCardEntitty;
import com.loutredev.pokecarte.persistences.repositories.SetCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/set-cards")
public class SetCardController {
    @Autowired
    private SetCardRepository setCardRepository;

    @Autowired
    private SetCardService setCardService;

    @GetMapping
    public ResponseEntity<List<SetCardResponseDTO>> getAllSetCard() {
        List<SetCardResponseDTO> setCards = setCardService.getAll();
        return ResponseEntity.ok(setCards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetCardResponseDTO> getById(@PathVariable String id) {
        SetCardResponseDTO setCard = setCardService.getById(id);
        return ResponseEntity.ok(setCard);
    }

    @GetMapping("/last")
    public ResponseEntity<SetCardResponseDTO> getLast() {
        SetCardResponseDTO setCard = setCardService.getLast();
        return ResponseEntity.ok(setCard);
    }
}
