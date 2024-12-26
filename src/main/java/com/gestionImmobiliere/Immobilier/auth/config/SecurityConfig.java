package com.gestionImmobiliere.Immobilier.auth.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {
    // Injection des valeurs de configuration
    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    /**
     * Configure Keycloak Authentication Provider
     */
    @Bean
    public KeycloakAuthenticationProvider keycloakAuthenticationProvider() {
        KeycloakAuthenticationProvider provider = new KeycloakAuthenticationProvider();
        provider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        return provider;
    }

    // Résolution des propriétés Keycloak
    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    /**
     * Configure Session Strategy for Stateless Authentication
     */
    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    /**
     * Bean JwtDecoder pour valider les tokens JWT avec une URL dynamique
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        String jwkSetUri = String.format("%s/realms/%s/protocol/openid-connect/certs", authServerUrl, realm);
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    /**
     * Configure Spring Security Filter Chain
     * Pour l'utilisation des apis des keycloaks
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactiver CSRF pour les APIs
                .authorizeHttpRequests(auth -> auth
                        //Pour permettre
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/api/**", "/create/**").permitAll()
                        // Autoriser les ressources Swagger UI
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/webjars/**"
                        ).permitAll()
                        // Protéger les routes ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Authentification requise pour toutes les autres routes
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt()); // Validation JWT
        return http.build();
    }

    /*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // URLs publiques
                        .anyRequest().authenticated() // Les autres URLs nécessitent une authentification
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/keycloak") // Redirection vers Keycloak pour login
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // Redirection après logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }*/

    /**
     * Configure Authentication Manager
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(keycloakAuthenticationProvider())
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }

    @Bean
    public LogoutHandler keycloakLogoutHandler() {
        return (request, response, authentication) -> {
            if (authentication != null) {
                // Redirection vers Keycloak pour un logout global
                String logoutUrl = "http://localhost:8080/realms/gestion-immobiliere/protocol/openid-connect/logout" +
                        "?redirect_uri=http://localhost:8084/api/login"; // URL de redirection après logout
                response.setStatus(302);
                response.setHeader("Location", logoutUrl);
            }
        };
    }

    @Bean
    public LogoutSuccessHandler keycloakLogoutSuccessHandler() {
        return (request, response, authentication) -> {
            response.sendRedirect("/"); // Redirection locale après logout
        };
    }
}
