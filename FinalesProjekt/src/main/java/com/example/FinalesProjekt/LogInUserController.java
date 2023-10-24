package com.example.FinalesProjekt;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LogInUserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findByEmail(email);

        Map<String, Object> response = new HashMap<>();

        if (user != null) {
            String hashedPassword = DigestUtils.sha256Hex(password);
            String storedPassword = user.getPassword();

            if (hashedPassword.equals(storedPassword)) {
                response.put("success", true);
                response.put("email", user.getEmail()); // Hier fügen wir die E-Mail hinzu
                response.put("name", user.getName()); // Hier fügen wir den Benutzernamen hinzu
                response.put("id", user.getId()); // Hier fügen wir die Benutzer-ID hinzu
            } else {
                response.put("success", false);
                response.put("message", "Ungültige Anmeldeinformationen");
            }
        }

        return ResponseEntity.ok(response);
    }
}
