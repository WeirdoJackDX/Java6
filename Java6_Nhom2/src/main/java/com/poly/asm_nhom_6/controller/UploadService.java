package com.poly.asm_nhom_6.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    File save(MultipartFile file, String folder) throws IOException;
}
