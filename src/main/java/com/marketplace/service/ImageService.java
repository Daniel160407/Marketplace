package com.marketplace.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {
    private static final String ROOT_NAME = "src/main/resources/static/images";

    private final Path photoRoot = Paths.get(ROOT_NAME);
    @PostConstruct
    private void init() {
        try {
            Files.createDirectories(photoRoot);
        } catch (IOException e) {
            throw new RuntimeException("Could not create or find root folder");
        }
    }

    public void storePhoto(MultipartFile photo, String name) {
        try {
            Files.copy(photo.getInputStream(), photoRoot.resolve(name), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not store photo: " + name, e);
        }
    }
}
