package com.loutredev.pokecarte;

import com.loutredev.pokecarte.persistences.entities.CustomerEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PokecarteApplication {
	public static void main(String[] args) {
		SpringApplication.run(PokecarteApplication.class, args);
	}
}
