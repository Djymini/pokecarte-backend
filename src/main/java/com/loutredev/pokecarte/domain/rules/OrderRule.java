package com.loutredev.pokecarte.domain.rules;

import com.loutredev.pokecarte.persistences.entities.ProductEntity;

import java.util.List;

public class OrderRule {
    public static void validateTotal(double total) {
        double max = 5000.0;
        if (total > max) {
            throw new RuntimeException("Le montant total de la commande dépasse le plafond autorisé (" + max + "€)");
        }
    }
}
