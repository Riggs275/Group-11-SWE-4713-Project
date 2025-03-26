package com.accountingAPI.accountingSoftware.util;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator{
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 12;

    public static String generateRandomPassword(){
        Random rnd = new SecureRandom();
        StringBuilder pass = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            pass.append(CHARACTERS.charAt(rnd.nextInt(CHARACTERS.length())));
        }
        
        return pass.toString();

    }

    public static String hashPassword(String password) {
        int hash = 7;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            hash = hash * 31 + (int) c;
            hash = hash ^ (hash << 5); // basic bit mixing
        }

        // Convert hash integer to hex string manually
        String hex = "";
        int tempHash = hash;
        for (int i = 0; i < 8; i++) {
            int nibble = tempHash & 0xF; // get last 4 bits
            char hexChar = (char)(nibble < 10 ? '0' + nibble : 'A' + (nibble - 10));
            hex = hexChar + hex;
            tempHash = tempHash >> 4;
        }

        return hex;
    }

}