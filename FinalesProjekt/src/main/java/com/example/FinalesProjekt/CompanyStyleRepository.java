package com.example.FinalesProjekt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyStyleRepository extends JpaRepository<CompanyStyle, Long> {


    CompanyStyle findByCompanyId(Long companyId);
}