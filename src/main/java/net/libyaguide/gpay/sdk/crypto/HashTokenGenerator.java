package net.libyaguide.gpay.sdk.crypto;

import java.security.SecureRandom;
import java.util.Base64;

public class HashTokenGenerator {
    public static String generateSalt() {
        byte[] salt = new byte[32];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String generateHashToken(String salt, String password) {
        return salt + password;
    }
}
