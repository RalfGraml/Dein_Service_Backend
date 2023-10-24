package com.example.FinalesProjekt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/apiMark")
public class FavouriteMarkOrDeleteController {

    @Autowired
    FavouriteRepository favouriteRepository;

    @PostMapping("/addOrDeleteFavourite")
    public ResponseEntity<Map<String, Object>> addFavourite(@RequestParam long userId, @RequestParam long companyId) {
        Favourite existingFavourite = favouriteRepository.findByUserAndCompany(userId, companyId);
        Map<String, Object> response = new HashMap<>();

        if (existingFavourite != null) {
            response.put("success", false);
            response.put("message", "Favourite existiert bereits.");
            favouriteRepository.delete(existingFavourite);
        } else {
            Favourite newFavourite = new Favourite();
            newFavourite.setUser(userId);
            newFavourite.setCompany(companyId);

            favouriteRepository.save(newFavourite);

            response.put("success", true);
            response.put("message", "Favourite erfolgreich angelegt.");
        }

        return ResponseEntity.ok(response);
    }
}

