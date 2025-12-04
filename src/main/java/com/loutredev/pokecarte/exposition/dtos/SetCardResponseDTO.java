package com.loutredev.pokecarte.exposition.dtos;

import com.loutredev.pokecarte.persistences.entities.ProductEntity;

import java.time.LocalDateTime;
import java.util.List;

public record SetCardResponseDTO(
        String id,
        String name,
        String logo,
        String releaseDate,
        List<ProductResponseDTO> products,
        LocalDateTime updatedAt
) {
}
