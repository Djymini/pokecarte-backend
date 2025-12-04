package com.loutredev.pokecarte.exposition.controllers;

import com.loutredev.pokecarte.exposition.dtos.LoginUserRequestDTO;
import com.loutredev.pokecarte.exposition.dtos.LoginUserResponseDTO;
import com.loutredev.pokecarte.exposition.dtos.RegisterUserRequestDTO;
import com.loutredev.pokecarte.persistences.entities.UserEntity;
import com.loutredev.pokecarte.persistences.repositories.UserRepository;
import com.loutredev.pokecarte.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserRequestDTO request) {
        boolean alreadyExists = userRepository.existsByEmail(request.email());
        if (alreadyExists) {
            String response = "Cet email est déjà utilisé !";
            return ResponseEntity.badRequest().body(response);
        }

        UserEntity user = request.toEntity();
        // 👇 On SET le mot de passe depuis le Controller, pas depuis le Mapper
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);

        String response = "Utilisateur inscrit avec succès !";
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDTO> authenticateUser(@RequestBody LoginUserRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
        String token = jwtUtil.generateToken(authenticatedUser);

        LoginUserResponseDTO response = LoginUserResponseDTO.fromEntity(token, authenticatedUser);
        return ResponseEntity.ok(response);
    }


}
