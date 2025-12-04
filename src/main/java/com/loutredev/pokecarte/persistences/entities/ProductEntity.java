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
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductEntity {
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String image;

    @Column(nullable = false)
    private String category;

    @Column()
    private String illustrator;

    @Column(nullable = false)
    private String rarity;

    @Column(nullable = false)
    private int hp;

    @Column()
    private String evolveFrom;

    @Column()
    private String description;

    @Column()
    private String stage;

    @Column(nullable = false)
    private int retreat;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private double discount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "set_id")
    private SetCardEntitty setCard;

    @ManyToMany
    @JoinTable(
            name = "product_type_card",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "type_card_id")
    )
    private List<TypeCardEntity> types = new ArrayList<>();

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private List<AttackCardEntity> attacks = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "product_weakness",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "type_card_id")
    )
    private List<TypeCardEntity> weaknesses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "product_resistance",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "type_card_id")
    )
    private List<TypeCardEntity> resistances = new ArrayList<>();

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
