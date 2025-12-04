package com.loutredev.pokecarte.mappers;

import com.loutredev.pokecarte.exposition.dtos.ProductRequestDTO;
import com.loutredev.pokecarte.exposition.dtos.ProductResponseDTO;
import com.loutredev.pokecarte.persistences.entities.ProductEntity;
import com.loutredev.pokecarte.persistences.entities.SetCardEntitty;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static ProductEntity toEntity(ProductRequestDTO dto, SetCardEntitty setCardEntitty) {
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setImage(dto.image());
        entity.setCategory(dto.category());
        entity.setIllustrator(dto.illustrator());
        entity.setRarity(dto.rarity());
        entity.setHp(dto.hp());
        entity.setDescription(dto.description());
        entity.setStage(dto.stage());
        entity.setRetreat(dto.retreat());
        entity.setPrice(dto.price());
        entity.setStock(dto.stock());
        entity.setDiscount(dto.discount());
        entity.setSetCard(setCardEntitty);
        entity.setTypes(dto.types());
        entity.setWeaknesses(dto.weakness());
        entity.setResistances(dto.resistances());
        return entity;
    }

    public static ProductResponseDTO toDto(ProductEntity entity) {
        return new ProductResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getImage(),
                entity.getCategory(),
                entity.getIllustrator(),
                entity.getRarity(),
                entity.getHp(),
                entity.getDescription(),
                entity.getStage(),
                entity.getRetreat(),
                entity.getPrice(),
                entity.getStock(),
                entity.getDiscount(),
                entity.getSetCard().getId(),
                entity.getTypes(),
                entity.getWeaknesses(),
                entity.getResistances()
        );
    }
}
