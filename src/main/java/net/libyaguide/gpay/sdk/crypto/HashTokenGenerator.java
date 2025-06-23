package net.libyaguide.gpay.sdk.crypto;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class for generating hash tokens and salts for request signing.
 */
public class HashTokenGenerator {
    /**
     * Generates a random salt encoded in Base64.
     * @return The generated salt string.
     */
    public static String generateSalt() {
        byte[] salt = new byte[32];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Generates a hash token by concatenating the salt and password.
     * @param salt The random salt.
     * @param password The password.
     * @return The generated hash token.
     */
    public static String generateHashToken(String salt, String password) {
        return salt + password;
    }
}
