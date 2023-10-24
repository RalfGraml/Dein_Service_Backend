package com.example.FinalesProjekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/apiCompanyPicture")
public class ImageUploadController {

    @Autowired
    CompanyStyleRepository companyStyleRepository;

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("companyId") Long companyId, @RequestParam("imageFile") MultipartFile file) {
        // Überprüfe, ob die Datei ein Bild ist
        if (!file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().body("Nur Bilddateien sind erlaubt.");
        }

        // Speichere das Bild im statischen Verzeichnis
        String fileName = "C:\\Users\\Codersbay\\Desktop\\Spring_Boot\\FinalesProjekt\\src\\main\\resources\\static\\img\\" + companyId + "_" + file.getOriginalFilename();

        try {
            file.transferTo(new File(fileName));


            CompanyStyle companyStyle = companyStyleRepository.findByCompanyId(companyId);
            if (companyStyle != null) {
                companyStyle.setImgPath(fileName);
                companyStyleRepository.save(companyStyle);
            }

            return ResponseEntity.ok("Bild erfolgreich hochgeladen.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Speichern des Bildes.");
        }
    }

    @PostMapping("/uploadBackroundImage")
    public ResponseEntity<String> uploadBackroundImage(@RequestParam("companyId") Long companyId, @RequestParam("imageFile") MultipartFile file) {
        // Überprüfe, ob die Datei ein Bild ist
        if (!file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().body("Nur Bilddateien sind erlaubt.");
        }

        // Speichere das Bild im statischen Verzeichnis
        String fileName = "C:\\Users\\Codersbay\\Desktop\\Spring_Boot\\FinalesProjekt\\src\\main\\resources\\static\\img\\" + companyId + "_" + file.getOriginalFilename();

        try {
            file.transferTo(new File(fileName));

            // Aktualisiere den Dateipfad in der Datenbank
            CompanyStyle companyStyle = companyStyleRepository.findByCompanyId(companyId);
            if (companyStyle != null) {
                String pathForCss="../Spring_Boot/FinalesProjekt/src/main/resources/static/img/"+companyId+"_"+file.getOriginalFilename();
                companyStyle.setBackroundImgPath(pathForCss);
                companyStyleRepository.save(companyStyle);
            }

            return ResponseEntity.ok("Hintergrundbild erfolgreich hochgeladen.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Speichern des Hintergrundbildes.");
        }
    }

    @PostMapping("getImage")
    public ResponseEntity<String> picPath(@RequestParam("companyId") Long companyId) {
        CompanyStyle companyStyle = companyStyleRepository.findByCompanyId(companyId);
        if (companyStyle != null) {
            return ResponseEntity.ok(companyStyle.getImgPath());
        } else {
            return ResponseEntity.badRequest().body("Kein Bild Vorhanden");
        }
    }

    @PostMapping("getBackroundImagePath")
    public ResponseEntity<String> backroundImagePath(@RequestParam("companyId") Long companyId) {
        CompanyStyle companyStyle = companyStyleRepository.findByCompanyId(companyId);
        if (companyStyle != null) {
            return ResponseEntity.ok(companyStyle.getBackroundImgPath());
        } else {
            return ResponseEntity.badRequest().body("Kein Hintergrundbild Vorhanden");
        }
    }
}
