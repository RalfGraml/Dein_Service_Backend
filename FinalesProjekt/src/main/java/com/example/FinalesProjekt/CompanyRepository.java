package com.example.FinalesProjekt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByEmail(String email);

    Company findByEmailAndPassword(String email, String password);

    Company findById(long id);

    List<Company> findByTownAndJob(String town, String job);
}
