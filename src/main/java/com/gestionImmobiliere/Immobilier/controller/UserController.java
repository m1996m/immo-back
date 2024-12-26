package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.user.UserDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController extends GenericController<User, UserDto> {

    public UserController(
            GenericService<User> genericService,
            GenericDtoMapper<UserDto, User> genericDtoMapper
    ) {
        super(genericService, genericDtoMapper);
    }

    @GetMapping("/userinfo")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaims(); // Retourne toutes les informations du token
    }

    @GetMapping("/username")
    public String getUsername(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaim("preferred_username"); // Retourne le nom d'utilisateur
    }
}

