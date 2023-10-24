package com.example.FinalesProjekt;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long favourite_id;

    @Column(name = "user_id")
    long user;

    @Column(name = "company_id")
    long company;
}
