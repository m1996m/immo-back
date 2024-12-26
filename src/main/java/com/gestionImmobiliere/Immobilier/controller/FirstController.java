package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.organisme.OrganismeDto;
import com.gestionImmobiliere.Immobilier.keycloak.KeycloakService;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.service.OrganismeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FirstController {
    private final OrganismeService organismeService;
    private final KeycloakService keycloakService;

    @Operation(summary = "Creation organisme")
    @PostMapping("/public/create/organisme")
    public Organisme create(@RequestBody OrganismeDto organismeDto){
        return organismeService.createOrganisme(organismeDto);
    }

    @Operation(summary = "Creation organisme")
    @GetMapping("/create/groupe/keycloak")
    public String createGroupe(@RequestParam("name") String name){
        return keycloakService.createGroup(name);
    }
}
