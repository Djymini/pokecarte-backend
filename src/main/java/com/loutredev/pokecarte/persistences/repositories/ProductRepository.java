package com.loutredev.pokecarte.persistences.repositories;

import com.loutredev.pokecarte.persistences.entities.ProductEntity;
import com.loutredev.pokecarte.persistences.entities.SetCardEntitty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    List<ProductEntity> findByNameContainingIgnoreCase(String keyword);
    List<ProductEntity> findByNameContainingIgnoreCaseAndPriceGreaterThanAndPriceLessThan(String keyword, double minPrice, double maxPrice);

    @Query("""
    SELECT p FROM ProductEntity p
    WHERE p.setCard.id IN :setCardIds
    AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))
    AND p.price >= :minPrice
    AND p.price <= :maxPrice"""
    )
    List<ProductEntity> findAllByFilters(
            @Param("name") String name,
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            @Param("setCardIds") List<String> setCardIds
    );
}
