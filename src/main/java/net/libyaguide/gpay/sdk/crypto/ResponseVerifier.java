package net.libyaguide.gpay.sdk.crypto;

import net.libyaguide.gpay.sdk.client.GPayApiClient;
import java.util.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class ResponseVerifier {
    public static boolean verifyResponse(String secretKey, String password, GPayApiClient.ApiResponse response, Map<String, String> responseFields) throws Exception {

        String salt = response.headers.get("X-Signature-Salt");
        String receivedHash = response.headers.get("X-Signature-Hash");
        String hashToken = salt + password;
        // Build query string
        TreeMap<String, String> sorted = new TreeMap<>(responseFields);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            if (sb.length() > 0) sb.append("&");
            sb.append(entry.getKey()).append("=");
            String value = entry.getValue();
            sb.append(value == null ? "" : value);
        }
        String verificationString = hashToken + sb.toString();
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] hash = sha256_HMAC.doFinal(verificationString.getBytes(StandardCharsets.UTF_8));
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) {
            hex.append(String.format("%02x", b));
        }
        String generatedHash = hex.toString();
        return generatedHash.equalsIgnoreCase(receivedHash);
    }
}
