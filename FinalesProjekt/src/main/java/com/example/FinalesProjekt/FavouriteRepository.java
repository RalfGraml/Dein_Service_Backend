package com.example.FinalesProjekt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    Favourite findByUserAndCompany(long user, long company);

    List<Favourite> findByUser(Long userId);
}

