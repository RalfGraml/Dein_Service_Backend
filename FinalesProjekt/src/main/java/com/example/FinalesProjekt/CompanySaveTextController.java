package com.example.FinalesProjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CompanySaveText")
public class CompanySaveTextController {

    @Autowired
    private CompanyStyleRepository companyStyleRepository;

    @PutMapping("/text")
    public ResponseEntity<?> saveText(@RequestParam Long companyId, @RequestParam String message) {
        CompanyStyle companyStyle = companyStyleRepository.findByCompanyId(companyId);

        if (companyStyle != null) {
            companyStyle.setTextField(message);
            companyStyleRepository.save(companyStyle);
            return ResponseEntity.ok().body("{\"success\": true}");
        } else {
            return ResponseEntity.badRequest().body("{\"success\": false}");
        }
    }
}
