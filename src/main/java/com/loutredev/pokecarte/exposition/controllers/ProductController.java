package com.loutredev.pokecarte.exposition.controllers;

import com.loutredev.pokecarte.domain.services.ProductService;
import com.loutredev.pokecarte.exposition.dtos.ProductRequestDTO;
import com.loutredev.pokecarte.exposition.dtos.ProductResponseDTO;
import com.loutredev.pokecarte.mappers.ProductMapper;
import com.loutredev.pokecarte.persistences.entities.ProductEntity;
import com.loutredev.pokecarte.persistences.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllByFilter(
            @RequestParam(required = false, name = "name", defaultValue = "") String name,
            @RequestParam(required = false, name = "minPrice", defaultValue = "0") Double minPrice,
            @RequestParam(required = false, name = "maxPrice", defaultValue = "10000") Double maxPrice,
            @RequestParam(required = false, name = "setIds") List<String> setIds
    ) {
        List<ProductResponseDTO> response = productService.getAllByFilter(name, minPrice, maxPrice, setIds);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable String id) {
        ProductEntity product = productRepository.findById(id).orElse(null);

        if (product != null) {
            ProductResponseDTO response = ProductMapper.toDto(product);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> search(@RequestParam String keyword) {
        List<ProductResponseDTO> dtos = productRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(ProductMapper::toDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO request) {
        System.out.println("Hello post");
        ProductResponseDTO response = productService.create(request);
        System.out.println("Hello postFinal");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<List<ProductResponseDTO>> update(@RequestBody List<ProductRequestDTO> productNewValueList){
        List<ProductResponseDTO> responseDTOList = new ArrayList<>();
        for(ProductRequestDTO product : productNewValueList){
            responseDTOList.add(productService.update(product));
        }
        return ResponseEntity.status(HttpStatus.GONE).body(responseDTOList);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        productService.delete(id);
    }
}
