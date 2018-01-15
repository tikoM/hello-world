package com.example.demo.user.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodingService {

    String encodePassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(hash);
    }
}
