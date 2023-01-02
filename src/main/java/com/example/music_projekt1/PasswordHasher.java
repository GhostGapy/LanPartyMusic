package com.example.music_projekt1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Use the SHA-256 algorithm to create a hash of the password
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] hash = md.digest();

        // Encode the hash as a base64 string
        return Base64.getEncoder().encodeToString(hash);
    }
}
