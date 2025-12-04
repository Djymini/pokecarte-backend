package com.loutredev.pokecarte.domain.services;

import com.loutredev.pokecarte.api.responses.TCGDEXResponse;
import com.loutredev.pokecarte.exceptions.ResourceNotFoundException;
import com.loutredev.pokecarte.exposition.dtos.ProductResponseDTO;
import com.loutredev.pokecarte.exposition.dtos.SetCardResponseDTO;
import com.loutredev.pokecarte.mappers.ProductMapper;
import com.loutredev.pokecarte.mappers.SetCardMapper;
import com.loutredev.pokecarte.persistences.entities.ProductEntity;
import com.loutredev.pokecarte.persistences.entities.SetCardEntitty;
import com.loutredev.pokecarte.persistences.repositories.SetCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SetCardService {
    @Autowired
    private SetCardRepository setCardRepository;

    public List<SetCardResponseDTO> getAll(){
        List<SetCardResponseDTO> response = new ArrayList<>();
        List<SetCardEntitty> setCards = setCardRepository.findAll();
        for (SetCardEntitty setCard : setCards){
            response.add(SetCardMapper.toDto(setCard, getProductListToDto(setCard.getProducts())));
        }
        return response;
    }

    public SetCardResponseDTO getById(String id){
        SetCardEntitty setCard = findById(id);
        return SetCardMapper.toDto(setCard, getProductListToDto(setCard.getProducts()));
    }

    public SetCardResponseDTO getLast(){
        SetCardEntitty setCard = setCardRepository.findByNameOrderByReleaseDateDesc("").getFirst();
        return SetCardMapper.toDto(setCard, getProductListToDto(setCard.getProducts()));
    }

    public SetCardEntitty findById(String id){
        return setCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Set avec l'ID : " + id + " n'existe pas."
                ));
    }

    public SetCardEntitty createSetCard(String id){
        if(isExited(id)){
            return findById(id);
        }else {
            TCGDEXResponse TGCDEXset = test2(id);
            return setCardRepository.save(new SetCardEntitty(id, TGCDEXset.getName(), TGCDEXset.getLogo(), TGCDEXset.getReleaseDate(), new ArrayList<>(), LocalDateTime.now(), LocalDateTime.now()));
        }
    }

    List<ProductResponseDTO> getProductListToDto(List<ProductEntity> productEntities){
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        for (ProductEntity productEntity : productEntities){
            productResponseDTOList.add(ProductMapper.toDto(productEntity));
        }
        return productResponseDTOList;
    }

    public TCGDEXResponse test2(String id) {
        String url = "https://api.tcgdex.net/v2/fr/sets/";
        String urlWithParams = UriComponentsBuilder.fromHttpUrl(url+id).toUriString();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TCGDEXResponse> result = restTemplate.exchange(
                urlWithParams,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TCGDEXResponse>() {}
        );
        return result.getBody();
    }

    public boolean isExited(String id){
        SetCardEntitty setCard = setCardRepository.findById(id).orElse(null);
        return setCard != null;
    }
}
