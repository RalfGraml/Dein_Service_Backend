package com.example.FinalesProjekt;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "company_id")
    long company;

    @Column(name = "user_id")
    long user;

    String status;


    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime time;


}
