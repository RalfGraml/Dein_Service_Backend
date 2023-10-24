package com.example.FinalesProjekt;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/apiCompany")
public class CompanyRegisterController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyStyleRepository companyStyleRepository;

    @PostMapping("/registerCompany")
    public ResponseEntity<Map<String, Object>> register(
            @RequestParam String email, @RequestParam String password,
            @RequestParam String name, @RequestParam String place,
            @RequestParam String town, @RequestParam String job
    ) {
        Company existingCompany = companyRepository.findByEmail(email);

        Map<String, Object> response = new HashMap<>();

        if (existingCompany != null) {
            response.put("success", false);
            response.put("message", "E-Mail-Adresse bereits registriert.");
        } else {
            String hashedPassword = DigestUtils.sha256Hex(password);

            Company newCompany = new Company();
            newCompany.setEmail(email);
            newCompany.setPassword(hashedPassword);
            newCompany.setName(name);
            newCompany.setPlace(place);
            newCompany.setTown(town);
            newCompany.setJob(job);

            // Speichere das Unternehmen in der Datenbank
            companyRepository.save(newCompany);
            Long companyId = newCompany.getId();

            CompanyStyle newCompanyStyle = new CompanyStyle();
            newCompanyStyle.setTextField("Hier bitte eigenen text eingeben");
            newCompanyStyle.setImgPath("C:\\Users\\Codersbay\\Desktop\\Spring_Boot\\FinalesProjekt\\src\\main\\resources\\static\\img\\enten.jpg");
            newCompanyStyle.setCompanyId(companyId);
            companyStyleRepository.save(newCompanyStyle);

            response.put("success", true);
            response.put("message", "Registrierung erfolgreich.");
        }

        return ResponseEntity.ok(response);
    }
}