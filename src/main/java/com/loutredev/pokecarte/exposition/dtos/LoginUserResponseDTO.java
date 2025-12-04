package com.loutredev.pokecarte.exposition.dtos;

import com.loutredev.pokecarte.persistences.entities.UserEntity;

public record LoginUserResponseDTO(
        String token,
        String email,
        String role
) {
    public static LoginUserResponseDTO fromEntity(String token, UserEntity user) {
        return new LoginUserResponseDTO(
                token,
                user.getEmail(),
                user.getRole().name()
        );
    }
}
