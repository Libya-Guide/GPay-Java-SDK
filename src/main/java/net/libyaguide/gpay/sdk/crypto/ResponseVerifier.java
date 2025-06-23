package net.libyaguide.gpay.sdk.crypto;

import net.libyaguide.gpay.sdk.client.GPayApiClient;
import java.util.*;

/**
 * Utility class for verifying API responses using HMAC signatures.
 */
public class ResponseVerifier {
    /**
     * Verifies the response signature using the provided secret key and password.
     * @param secretKey The secret key for HMAC.
     * @param password The password used in hash token generation.
     * @param response The API response to verify.
     * @param responseFields The response fields to include in the hash.
     * @return true if the response is valid, false otherwise.
     * @throws Exception if verification fails.
     */
    public static boolean verifyResponse(
        String secretKey, String password, GPayApiClient.ApiResponse response, 
        Map<String, String> responseFields
    ) throws Exception {
        String salt = response.headers.get("x-signature-salt");
        String receivedHash = response.headers.get("x-signature-hash");
        String hashToken = salt + password;
        String generatedHash = VerificationHashGenerator
            .generateVerificationHash(hashToken, responseFields, secretKey);
        return generatedHash.equals(receivedHash);
    }
}
