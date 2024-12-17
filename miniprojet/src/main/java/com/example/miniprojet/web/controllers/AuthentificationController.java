package com.example.miniprojet.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthentificationController {

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // L'utilisateur est déjà connecté, rediriger vers une autre page
            return "redirect:/espaceadmin";
        }
        // Sinon, afficher la page de connexion
        return "login";
    }
  

    
}
