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
@Table(name = "attack_card")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttackCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String[] cost;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String effect;

    @Column(nullable = false)
    private int damage;

    @ManyToMany
    @JoinTable(
            name = "cost_card",
            joinColumns = @JoinColumn(name = "attack_id"),
            inverseJoinColumns = @JoinColumn(name = "type_card_id")
    )
    private List<TypeCardEntity> types = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

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
