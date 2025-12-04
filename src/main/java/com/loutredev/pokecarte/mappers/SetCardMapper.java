package com.loutredev.pokecarte.mappers;

import com.loutredev.pokecarte.exposition.dtos.ProductResponseDTO;
import com.loutredev.pokecarte.exposition.dtos.SetCardRequestDTO;
import com.loutredev.pokecarte.exposition.dtos.SetCardResponseDTO;
import com.loutredev.pokecarte.persistences.entities.ProductEntity;
import com.loutredev.pokecarte.persistences.entities.SetCardEntitty;

import java.util.List;

public class SetCardMapper {
    public static SetCardEntitty toEntity(SetCardRequestDTO dto, List<ProductEntity> productEntityList) {
        SetCardEntitty entity = new SetCardEntitty();
        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setLogo(dto.logo());
        entity.setProducts(productEntityList);
        entity.setReleaseDate(dto.releaseDate());
        return entity;
    }

    public static SetCardResponseDTO toDto(SetCardEntitty entity, List<ProductResponseDTO> productResponseDTOList) {
        return new SetCardResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getLogo(),
                entity.getReleaseDate(),
                productResponseDTOList,
                entity.getUpdatedAt()
        );
    }
}
