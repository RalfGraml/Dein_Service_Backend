package com.example.FinalesProjekt;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RegisterUserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestParam String email, @RequestParam String password, @RequestParam String name) {
        User existingUser = userRepository.findByEmail(email);

        Map<String, Object> response = new HashMap<>();

        if (existingUser != null) {
            response.put("success", false);
            response.put("message", "E-Mail-Adresse bereits registriert.");
        } else {
            String hashedPassword = DigestUtils.sha256Hex(password);

            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(hashedPassword);
            newUser.setName(name);

            userRepository.save(newUser);
            response.put("success", true);
            response.put("message", "Registrierung erfolgreich.");
        }

        return ResponseEntity.ok(response);
    }
}