package net.libyaguide.gpay.sdk.crypto;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility class for generating verification hashes using HMAC-SHA256.
 */
public class VerificationHashGenerator {
    /**
     * Generates an HMAC-SHA256 hash of the given data using the provided secret key.
     * @param data The data to hash.
     * @param secretKey The secret key for HMAC.
     * @return The Base64-encoded HMAC-SHA256 hash.
     * @throws Exception if the hashing fails.
     */
    public static String generateHmacSHA256(String data, String secretKey) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] hash = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
    /**
     * Generates a verification hash for request/response validation.
     * @param hashToken The hash token (salt + password).
     * @param params The parameters to include in the hash.
     * @param secretKey The secret key for HMAC.
     * @return The Base64-encoded verification hash.
     * @throws Exception if the hashing fails.
     */
    public static String generateVerificationHash(
        String hashToken, Map<String, String> params, String secretKey
    ) throws Exception {
        TreeMap<String, String> sorted = new TreeMap<>(params);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append("=");
            String value = entry.getValue();
            if(value != null) {
                sb.append(value);
            }
        }

        String verificationString = hashToken + sb.toString();
        return generateHmacSHA256(verificationString, secretKey);
    }
}
