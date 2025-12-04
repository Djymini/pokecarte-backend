package com.loutredev.pokecarte.exposition.dtos;

import com.loutredev.pokecarte.persistences.entities.TypeCardEntity;

import java.util.List;

public record ProductResponseDTO(
        String id,
        String name,
        String image,
        String category,
        String illustrator,
        String rarity,
        int hp,
        String description,
        String stage,
        int retreat,
        double price,
        int stock,
        double discount,
        String setId,
        List<TypeCardEntity> types,
        List<TypeCardEntity> weakness,
        List<TypeCardEntity> resistances
) {
}
