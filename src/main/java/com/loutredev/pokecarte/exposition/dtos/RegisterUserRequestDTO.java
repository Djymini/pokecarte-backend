package com.loutredev.pokecarte.exposition.dtos;

import com.loutredev.pokecarte.persistences.entities.Role;
import com.loutredev.pokecarte.persistences.entities.UserEntity;

public record RegisterUserRequestDTO(
        String email,
        String password
) {
    public UserEntity toEntity() {
        UserEntity user = new UserEntity(email, password, Role.USER);
        return user;
    }
}
