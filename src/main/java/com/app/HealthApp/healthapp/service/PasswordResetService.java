package com.app.HealthApp.healthapp.service;

import com.app.HealthApp.healthapp.model.User;
import com.app.HealthApp.healthapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    // Generiranje tokena za resetiranje i slanje na korisnikovov email (pohranjenu u korisničkom imenu)
    public void requestPasswordReset(String email) {
        User user = userRepository.findByUsername(email) // Koristi findByUsername za pronalaženje korisnika putem emaila
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        // Generiranje nasumičnog tokena koristeći Math.random()
        String resetToken = String.valueOf((int) (Math.random() * 900000) + 100000); // 6-digit token
        user.setResetToken(resetToken);
        userRepository.save(user);

        // Šalje token za resetiranje mailom
        emailService.sendResetToken(email, resetToken);
    }

    // Resetiranje korisničke lozinke korištenjem tokena
    public void resetPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken)
                .orElseThrow(() -> new RuntimeException("Pogrešan token"));

        user.setPassword(passwordEncoder.encode(newPassword)); // Hash the new password
        user.setResetToken(null); // Clear the reset token
        userRepository.save(user);
    }
}
