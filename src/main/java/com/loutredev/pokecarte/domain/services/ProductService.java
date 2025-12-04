package com.loutredev.pokecarte.domain.services;

import com.loutredev.pokecarte.domain.rules.ProductRule;
import com.loutredev.pokecarte.exceptions.ResourceNotFoundException;
import com.loutredev.pokecarte.exposition.dtos.ProductRequestDTO;
import com.loutredev.pokecarte.exposition.dtos.ProductResponseDTO;
import com.loutredev.pokecarte.mappers.ProductMapper;
import com.loutredev.pokecarte.persistences.entities.ProductEntity;
import com.loutredev.pokecarte.persistences.entities.SetCardEntitty;
import com.loutredev.pokecarte.persistences.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SetCardService setCardService;

    public ProductResponseDTO create(ProductRequestDTO request){
        System.out.println("Hello post1");
        ProductRule.validateBeforeCreation(request);
        if(!productIsExited(request.id())){
            System.out.println("Hello post2");
            return ProductMapper.toDto(save(request));
        }else{
            System.out.println("Hello post2bis");
            throw new RuntimeException("Le produit avec l'id : " + request.id() + "existe déjà");
        }
    }

    public ProductResponseDTO update(ProductRequestDTO request){
        ProductEntity entity = ProductMapper.toEntity(request, setCardService.findById(request.id()));
        ProductRule.validateBeforeUpdate(entity);
        if(productIsExited(request.id())){
            return ProductMapper.toDto(save(request));
        }else{
            throw new ResourceNotFoundException("Le produit avec l'id : " + request.id() + "n'existe pas");
        }
    }

    public ProductEntity save(ProductRequestDTO request){
        System.out.println("Hello post3 request : " + request.id());
        SetCardEntitty newSetCard = setCardService.createSetCard(request.setCardId());
        System.out.println("Hello post3 newsetCard : " + newSetCard.getId());
        ProductEntity entity = ProductMapper.toEntity(request, newSetCard);
        System.out.println("Hello post3 entity : " + entity.getId());
        try {
            return productRepository.save(entity);
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException("Echec de la mise à jour du produit avec l'Id : " + request.id() + ", detail : " + e.getMessage());
        }
    }

    public List<ProductResponseDTO> getAllByFilter(String name, Double minPrice, Double maxPrice, List<String> setIds){
        if(setIds == null || setIds.isEmpty()){
            return productRepository.findByNameContainingIgnoreCaseAndPriceGreaterThanAndPriceLessThan(name, minPrice, maxPrice)
                    .stream()
                    .map(ProductMapper::toDto)
                    .toList();
        }else {
            return productRepository.findAllByFilters(name, minPrice, maxPrice, setIds)
                    .stream()
                    .map(ProductMapper::toDto)
                    .toList();
        }
    }

    public void delete(String id){
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Produit avec l'ID : " + id + " n'existe pas."
        ));
        productRepository.delete(productEntity);
    }

    public boolean productIsExited(String id){
        ProductEntity product = productRepository.findById(id).orElse(null);
        return product != null;
    }
}
