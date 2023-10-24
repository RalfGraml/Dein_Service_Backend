package com.example.FinalesProjekt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apiSearch")
public class SearchCompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @PostMapping("/findCompany")
    public ResponseEntity<List<Company>> foundCompanys(@RequestParam String town, @RequestParam String job) {
        List<Company> existingCompanies = companyRepository.findByTownAndJob(town, job);

        if (existingCompanies != null && !existingCompanies.isEmpty()) {
            return ResponseEntity.ok(existingCompanies);
        } else {
            return ResponseEntity.noContent().build(); // Return status code 204 (No Content)
        }
    }
}
