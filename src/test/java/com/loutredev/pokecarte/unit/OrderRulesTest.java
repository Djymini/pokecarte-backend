package com.loutredev.pokecarte.unit;

import com.loutredev.pokecarte.domain.rules.OrderRule;
import com.loutredev.pokecarte.persistences.entities.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class OrderRulesTest {
    private ProductEntity activeProduct;
    private ProductEntity inactiveProduct;

    @BeforeEach
    void setup() {
        activeProduct = new ProductEntity();
        activeProduct.setId("test-4500");
        activeProduct.setName("Magic Potion");
        activeProduct.setPrice(100.0);
        activeProduct.setStock(10);
        activeProduct.setImage("");
        activeProduct.setCategory("Pokemon");
        activeProduct.setIllustrator("tetsuya koizumi");
        activeProduct.setRarity("Commune");
        activeProduct.setHp(100);
        activeProduct.setStage("Base");
        activeProduct.setRetreat(1);
        activeProduct.setDiscount(0.0);
    }

    @Test
    @DisplayName("Should throw if total exceeds allowed limit")
    void shouldThrowIfTotalExceedsLimit() {
        double total = 6000.0;

        Exception ex = assertThrows(RuntimeException.class,
                () -> OrderRule.validateTotal(total));

        assertTrue(ex.getMessage().contains("plafond"));
    }

    @Test
    @DisplayName("Should pass if total is under allowed limit")
    void shouldPassIfTotalUnderLimit() {
        double total = 4999.99;

        assertDoesNotThrow(() -> OrderRule.validateTotal(total));
    }
}
