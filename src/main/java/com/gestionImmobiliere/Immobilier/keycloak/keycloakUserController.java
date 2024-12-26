package com.gestionImmobiliere.Immobilier.keycloak;

import com.gestionImmobiliere.Immobilier.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class keycloakUserController {
    private final KeycloakService keycloakService;

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        String result = keycloakService.createUser(userDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/update-user")
    public String updateUserByEmail(
            @RequestBody UserDto userDto,
            @RequestParam("email") String email
    ) {
        return keycloakService.updateUserByEmail(email,userDto);
    }

    @GetMapping("/reset/password")
    public String resetPassword(
            @RequestParam("idUser") String idUser,
            @RequestParam("password") String password
    ) {
        return keycloakService.resetPassword(idUser,password);
    }

    @GetMapping("/user/connected")
    public Map<String, Object> updateUserConnected() {
        return keycloakService.getUserDetails();
    }

    @GetMapping("/active/desactive")
    public String activeOrDesactive(
            @RequestParam("idUser") String idUser,
            @RequestParam("statut") boolean statut
    ) {
        return keycloakService.activateOrDeactivateUser(idUser, statut);
    }
}
