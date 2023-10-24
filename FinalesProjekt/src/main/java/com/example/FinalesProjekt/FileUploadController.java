package com.example.FinalesProjekt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class FileUploadController {

    @GetMapping("/")
    public String showUploadForm() {
        return "uploadForm";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestPart("text") String text, Model model) {
        if (!file.isEmpty()) {
            try {
                // Speichern des Bildes
                byte[] bytes = file.getBytes();
                File uploadDir = new File("uploads");
                if (!uploadDir.exists()) uploadDir.mkdir();
                File uploadedFile = new File(uploadDir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                file.transferTo(uploadedFile);

                // Speichern des Textes
                File textFile = new File(uploadDir.getAbsolutePath() + File.separator + "text.txt");
                textFile.createNewFile();
                Files.write(textFile.toPath(), text.getBytes());

                model.addAttribute("message", "Bild und Text erfolgreich hochgeladen.");
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "Fehler beim Hochladen der Datei.");
            }
        } else {
            model.addAttribute("message", "Bitte wähle eine Datei aus.");
        }

        return "uploadForm";
    }


}
