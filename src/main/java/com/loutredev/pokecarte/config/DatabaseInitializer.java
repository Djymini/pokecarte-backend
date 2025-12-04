package com.loutredev.pokecarte.config;

import com.loutredev.pokecarte.persistences.repositories.ProductRepository;
import com.loutredev.pokecarte.persistences.repositories.SetCardRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DatabaseInitializer {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {
        System.out.println("Test coucou");
        if (productRepository.count() > 0) return;
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("😱 Base vide : exécution de data.sql...");
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("data.sql"));
            System.out.println("🤭 Données initiales insérées avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
