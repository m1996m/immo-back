package com.gestionImmobiliere.Immobilier.keycloak;

import com.gestionImmobiliere.Immobilier.dto.user.UserDto;
import com.gestionImmobiliere.Immobilier.dto.user.UserDtoMapper;
import com.gestionImmobiliere.Immobilier.model.User;
import com.gestionImmobiliere.Immobilier.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final UserService userService;
    private final UserDtoMapper userMapper;

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl ;

    @Value("${keycloak.realm}")
    private String realm = "gestion-immobiliere";

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.admin.username}")
    private String adminUsername;

    @Value("${keycloak.admin.password}")
    private String adminPassword;

    /**
     * Récupère le token d'administration depuis Keycloak
     */
    private String getAdminToken() {
        String tokenUrl = String.format("%s/realms/master/protocol/openid-connect/token", keycloakUrl);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", "admin-cli");
        body.add("username", adminUsername);
        body.add("password", adminPassword);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

        return response.getBody().get("access_token").toString();
    }

    /**
     * Modification d'un user à partir d'un email
     * @param email
     * @param userUpdateDto
     * @return
     */
    public String updateUserByEmail(String email, UserDto userUpdateDto) {
        String searchUrl = String.format("%s/admin/realms/%s/users?email=%s", keycloakUrl, realm, email);

        String adminToken = getAdminToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(searchUrl, HttpMethod.GET, request, List.class);

        if (response.getBody() == null || response.getBody().isEmpty()) {
            return "User not found";
        }

        Map<String, Object> user = (Map<String, Object>) response.getBody().get(0);
        String userId = user.get("id").toString();

        Map<String, Object> updates = new HashMap<>();
        if (userUpdateDto.getFirstName() != null) {
            updates.put("firstName", userUpdateDto.getFirstName());
        }
        if (userUpdateDto.getLastName() != null) {
            updates.put("lastName", userUpdateDto.getLastName());
        }
        if (userUpdateDto.getEmail() != null) {
            updates.put("email", userUpdateDto.getEmail());
        }

        String updateUrl = String.format("%s/admin/realms/%s/users/%s", keycloakUrl, realm, userId);

        HttpEntity<Map<String, Object>> updateRequest = new HttpEntity<>(updates, headers);
        restTemplate.exchange(updateUrl, HttpMethod.PUT, updateRequest, Void.class);

        return "User updated successfully";
    }

    /**
     * Gestion du statut de l'user
     * @param userId
     * @param isEnabled
     * @return
     */
    public String activateOrDeactivateUser(String userId, boolean isEnabled) {
        String url = String.format("%s/admin/realms/%s/users/%s", keycloakUrl, realm, userId);

        String adminToken = getAdminToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of("enabled", isEnabled);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

        return isEnabled ? "User activated" : "User deactivated";
    }

    /**
     * Crée un nouvel utilisateur dans Keycloak
     * @param userDto
     * @return
     */
    public String createUser(UserDto userDto) {
        String createUserUrl = String.format("%s/admin/realms/%s/users", keycloakUrl, realm);

        String adminToken = getAdminToken();

        // Corps de la requête pour créer un utilisateur
        Map<String, Object> user = Map.of(
                //"username", userDto.getUsername(),
                "enabled", true,
                "email", userDto.getEmail(),
                "firstName", userDto.getFirstName(),
                "lastName", userDto.getLastName(),
                "credentials", new Object[]{
                        Map.of("type", "password", "value", userDto.getPassword(), "temporary", false)
                }
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(user, headers);

        try {
            //createGroup(userDto.getOrganisme());
            restTemplate.exchange(createUserUrl, HttpMethod.POST, request, Void.class);
            userService.create(userMapper.toEntity(userDto));

            // Ajouter l'utilisateur au groupe correspondant (Organisme)
            String userId = getUserIdByUsername(userDto.getEmail(), adminToken);
            String groupId = getGroupIdByName(userDto.getOrganisme(), adminToken);
            assignUserToGroup(userId, groupId, adminToken);

            return "User created successfully!";
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }
    }

    /**
     * Creation d'un nouveau group
     * @param groupName
     * @return
     */
    public String createGroup(String groupName) {
        String groupUrl = String.format("%s/admin/realms/%s/groups", keycloakUrl, realm);
        String token = getAdminToken();

        // Préparer le body
        Map<String, Object> groupBody = Map.of("name", groupName);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(groupBody, headers);

        try {
            restTemplate.exchange(groupUrl, HttpMethod.POST, request, Void.class);
            return "Group '" + groupName + "' created successfully!";
        } catch (Exception e) {
            return "Error creating group: " + e.getMessage();
        }
    }

    /**
     * Recuperation de l'user en fonction de username
     * @param username
     * @param token
     * @return
     */
    private String getUserIdByUsername(String username, String token) {
        String url = String.format("%s/admin/realms/%s/users?username=%s", keycloakUrl, realm, username);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        ResponseEntity<Map[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Map[].class);
        return response.getBody()[0].get("id").toString();
    }

    /**
     * Recuperation du group
     * @param groupName
     * @param token
     * @return
     */
    private String getGroupIdByName(String groupName, String token) {
        String url = String.format("%s/admin/realms/%s/groups", keycloakUrl, realm);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        ResponseEntity<Map[]> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Map[].class);

        for (Map group : response.getBody()) {
            if (group.get("name").equals(groupName)) {
                return group.get("id").toString();
            }
        }
        throw new RuntimeException("Group not found: " + groupName);
    }

    /**
     * Ajout d'un d'un user à un group
     * @param userId
     * @param groupId
     * @param token
     */
    private void assignUserToGroup(String userId, String groupId, String token) {
        String url = String.format("%s/admin/realms/%s/users/%s/groups/%s", keycloakUrl, realm, userId, groupId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        restTemplate.put(url, new HttpEntity<>(headers));
    }

    /**
     * Recuperation des informations de user connecté
     * @return
     */
    public Map<String, Object> getUserDetails() {
        // Récupérer l'authentification courante
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt)) {
            return null;
        }

        // Extraire le JWT
        Jwt jwt = (Jwt) authentication.getPrincipal();

        // Retourner toutes les claims
        return jwt.getClaims();
    }

    public User getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        // Extraire les informations supplémentaires du JWT
        if (authentication.getPrincipal() instanceof org.springframework.security.oauth2.jwt.Jwt) {
            var jwt = (org.springframework.security.oauth2.jwt.Jwt) authentication.getPrincipal();

            return userService.findUniqueWithValueAndAttribut(jwt.getClaim("email"), "email");
        }

        return null;
    }

    /**
     * Recuperation du mot de passe
     * @param userId
     * @param newPassword
     * @return
     */
    public String resetPassword(String userId, String newPassword) {
        // Obtenir le token d'administration
        String adminToken = getAdminToken();

        // Construire l'URL pour réinitialiser le mot de passe
        String url = String.format("%s/admin/realms/%s/users/%s/reset-password", keycloakUrl, realm, userId);

        // Créer le payload pour le mot de passe
        Map<String, Object> passwordPayload = new HashMap<>();
        passwordPayload.put("type", "password");
        passwordPayload.put("value", newPassword);
        passwordPayload.put("temporary", false); // false signifie que l'utilisateur n'a pas besoin de le changer au prochain login

        // Configurer les headers avec le token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(adminToken);

        // Construire la requête
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(passwordPayload, headers);

        // Envoyer la requête pour réinitialiser le mot de passe
        ResponseEntity<Void> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                request,
                Void.class
        );

        // Vérifier la réponse
        if (response.getStatusCode().is2xxSuccessful()) {
            return "Mot de passe réinitialisé avec succès !";
        } else {
            throw new RuntimeException("Échec de la réinitialisation du mot de passe. Code: " + response.getStatusCode());
        }
    }

    /**
     * Modifie le mot de passe d'un utilisateur
     * @param userId
     * @param newPassword
     */
    public void changePassword(String userId, String newPassword) {
        String url = String.format("%s/admin/realms/%s/users/%s/reset-password", keycloakUrl, realm, userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(getAdminToken());

        Map<String, Object> body = new HashMap<>();
        body.put("type", "password");
        body.put("value", newPassword);
        body.put("temporary", false);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        restTemplate.put(url, request);
    }

    /**
     * Verification si le mot de passe saisi est actuel mot de passe
     * @param username
     * @param currentPassword
     * @return
     */
    public boolean isCurrentPasswordValid(String username, String currentPassword) {
        String tokenUrl = String.format("%s/realms/%s/protocol/openid-connect/token", keycloakUrl, realm);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", clientId);
        body.add("username", username);
        body.add("password", currentPassword);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

            // Si la requête réussit et renvoie un token, le mot de passe est valide
            return response.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException e) {
            // Si l'erreur est 401 ou 400, le mot de passe est incorrect
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED || e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                return false;
            }
            // Autres exceptions
            throw new RuntimeException("Erreur lors de la vérification du mot de passe", e);
        }
    }
}

