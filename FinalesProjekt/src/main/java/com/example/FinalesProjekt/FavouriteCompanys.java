package com.example.FinalesProjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apiFavourite")
public class FavouriteCompanys {

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/getFavourites")
    public ResponseEntity<List<Company>> getFavorites(@RequestParam Long userId) {
        List<Favourite> favorites = favouriteRepository.findByUser(userId);
        List<Company> favoriteCompanies = new ArrayList<>();

        for (Favourite favourite : favorites) {
            Long companyId = favourite.getCompany();
            Company company = companyRepository.findById(companyId).orElse(null);

            if (company != null) {
                favoriteCompanies.add(company);
            }
        }

        return ResponseEntity.ok(favoriteCompanies);
    }
}

