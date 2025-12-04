package com.loutredev.pokecarte.domain.rules;

import com.loutredev.pokecarte.exposition.dtos.ProductRequestDTO;
import com.loutredev.pokecarte.persistences.entities.ProductEntity;

public class ProductRule {
    public static void validateBeforeCreation(ProductRequestDTO product) {
        checkAttribute(product);
    }

    public static void validateBeforeUpdate(ProductEntity product) {
        checkAttribute(product);
    }

    private static void checkAttribute(ProductRequestDTO product){
        if (product.price() <= 0) {
            throw new RuntimeException("Le prix doit être supérieur à 0.");
        }
        if (product.stock() < 0) {
            throw new RuntimeException("Le stock ne peut pas être négatif.");
        }
        if (product.discount() < 0) {
            throw new RuntimeException("La valeur de la promotion ne peut pas être négatif.");
        }
    }

    private static void checkAttribute(ProductEntity product){
        if (product.getPrice() <= 0) {
            throw new RuntimeException("Le prix doit être supérieur à 0.");
        }
        if (product.getStock() < 0) {
            throw new RuntimeException("Le stock ne peut pas être négatif.");
        }
        if (product.getDiscount() < 0) {
            throw new RuntimeException("La valeur de la promotion ne peut pas être négatif.");
        }
    }
}
