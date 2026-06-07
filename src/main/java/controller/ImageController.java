package com.library.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
@CrossOrigin("*")
public class ImageController {

    // This points directly to the 'uploads' folder you just created!
    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 1. Give the image a random ID so two "cover.jpg" files don't overwrite each other
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // 2. Build the exact path where it will be saved
            Path filePath = Paths.get(UPLOAD_DIR + fileName);

            // 3. Save the physical file to your computer!
            Files.write(filePath, file.getBytes());

            // 4. Send just the filename back to React so it can save it in the database
            return ResponseEntity.ok(fileName);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload image");
        }
    }
}