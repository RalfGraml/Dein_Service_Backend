package com.example.FinalesProjekt;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CompanyLoginService {
    @Autowired
    private CompanyRepository companyRepository;

    @NotNull
    public Map<String, Object> login(String email, String password) {
        Company company = companyRepository.findByEmail(email);

        Map<String, Object> response = new HashMap<>();

        if (company != null) {
            String hashedPassword = DigestUtils.sha256Hex(password);
            String storedPassword = company.getPassword();

            if (hashedPassword.equals(storedPassword)) {
                response.put("success", true);
                response.put("id", company.getId()); // Hier fügen wir die Benutzer-ID hinzu
                response.put("name",company.getName());
            } else {
                response.put("success", false);
                response.put("message", "Ungültige Anmeldeinformationen");
            }
        }
        return response;
    }
}
