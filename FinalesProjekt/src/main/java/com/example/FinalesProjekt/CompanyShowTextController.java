package com.example.FinalesProjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiCompanyShowText")
public class CompanyShowTextController {

    @Autowired
    private CompanyStyleRepository companyStyleRepository;

    @PostMapping("/getText")
    public ResponseEntity<String> getText(@RequestParam Long companyId) {
        CompanyStyle companyStyle = companyStyleRepository.findByCompanyId(companyId);

        if (companyStyle != null) {
            String textField = companyStyle.getTextField();
            return ResponseEntity.ok(textField);
        } else {
            return ResponseEntity.badRequest().body("");
        }
    }
}
