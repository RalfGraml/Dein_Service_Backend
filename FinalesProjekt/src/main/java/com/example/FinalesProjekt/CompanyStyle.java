package com.example.FinalesProjekt;

import jakarta.persistence.*;
import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "companystyle")
public class CompanyStyle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_style_id")
    private Long companyStyleId;

    @Column(name = "text_field")
    private String textField;

    @Column(name = "img_path")
    private String imgPath;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "backround_img_path")
    private String backroundImgPath;
}
