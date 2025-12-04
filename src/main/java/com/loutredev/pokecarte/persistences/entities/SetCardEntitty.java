package com.loutredev.pokecarte.persistences.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "set_card")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SetCardEntitty {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = false)
    private String releaseDate;

    @OneToMany(
            mappedBy = "setCard",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private List<ProductEntity> products = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
