package com.gestionImmobiliere.Immobilier.auth.Controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @GetMapping("/api/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }
}

