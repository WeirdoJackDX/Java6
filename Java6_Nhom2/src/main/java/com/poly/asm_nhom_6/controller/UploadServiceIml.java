package com.poly.asm_nhom_6.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadServiceIml implements UploadService {
    @Autowired
    ServletContext app;

    @Override
    public File save(MultipartFile img, String folder) throws IOException {
        File file = new ClassPathResource("static/" + folder).getFile();
        String s = System.currentTimeMillis() + img.getOriginalFilename();
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
        Path path = Paths.get(file.getAbsolutePath() + "/"+name);
        Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        File savedFile = new File(file, name);
        return savedFile;
    }
}
