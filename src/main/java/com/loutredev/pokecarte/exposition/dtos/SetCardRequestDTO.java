package com.loutredev.pokecarte.exposition.dtos;

import com.loutredev.pokecarte.persistences.entities.ProductEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record SetCardRequestDTO(
        String id,
        String name,
        String logo,
        String releaseDate,
        List<ProductRequestDTO> products
) {}
