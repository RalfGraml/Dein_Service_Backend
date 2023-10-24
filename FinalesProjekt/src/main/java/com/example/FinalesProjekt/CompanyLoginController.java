package com.example.FinalesProjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/CompanyLogin")
public class CompanyLoginController {

    @Autowired
    private CompanyLoginService service;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String email, @RequestParam String password) {
        Map<String, Object> response = service.login(email, password);

        return ResponseEntity.ok(response);
    }


}
