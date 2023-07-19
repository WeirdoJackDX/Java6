package com.poly.asm_nhom_6.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Service;

//Mã hóa đơn giản
@Service
public class EncryptionService {
    public String encrypt(String data) {
        byte[] encodedBytes = Base64.getEncoder().encode(data.getBytes(StandardCharsets.UTF_8));
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }

    public String decrypt(String encryptedData) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData.getBytes(StandardCharsets.UTF_8));
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
