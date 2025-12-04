package com.loutredev.pokecarte.exposition.dtos;

import com.loutredev.pokecarte.persistences.entities.SetCardEntitty;
import com.loutredev.pokecarte.persistences.entities.TypeCardEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record ProductRequestDTO(
        String id,

        @NotBlank(message = "Le nom du produit ne peut pas être vide")
        String name,

        String image,

        String category,

        String illustrator,

        String rarity,

        @PositiveOrZero(message = "Le stock ne peut pas être négatif")
        int hp,

        String description,

        String stage,

        @PositiveOrZero(message = "Le stock ne peut pas être négatif")
        int retreat,

        @PositiveOrZero(message = "Le stock ne peut pas être négatif")
        double price,

        @PositiveOrZero(message = "Le stock ne peut pas être négatif")
        int stock,

        @PositiveOrZero(message = "Le stock ne peut pas être négatif")
        double discount,

        String setCardId,

        List<TypeCardEntity> types,

        List<TypeCardEntity> weakness,

        List<TypeCardEntity> resistances
) {}
