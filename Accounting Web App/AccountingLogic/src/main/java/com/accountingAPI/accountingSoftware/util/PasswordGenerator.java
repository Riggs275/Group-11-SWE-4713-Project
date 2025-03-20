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
}