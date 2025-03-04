package com.app.HealthApp.healthapp.controller;

import com.app.HealthApp.healthapp.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/password-reset")
    public String showPasswordResetPage() {
        return "password-reset";
    }

    @PostMapping("/request-reset-token") //Endpoint zaslužan za slanje tokena
    public String requestResetToken(@RequestParam String email, Model model) {
        passwordResetService.requestPasswordReset(email);
        model.addAttribute("message", "Token je poslan na email.");
        model.addAttribute("emailSent", true);
        return "password-reset";
    }

    @PostMapping("/reset-password")
    public String handlePasswordReset(
            @RequestParam String token,
            @RequestParam String newPassword,
            Model model
    ) {
        try {
            passwordResetService.resetPassword(token, newPassword);
            model.addAttribute("message", "Lozinka uspješno promijenjena. Možete se prijaviti.");
        } catch (Exception e) {
            model.addAttribute("error", "Krivi token ili greška u promjeni lozinke.");
        }
        return "password-reset";
    }
}