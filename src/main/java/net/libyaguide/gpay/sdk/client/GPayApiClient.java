package net.libyaguide.gpay.sdk.client;

import net.libyaguide.gpay.sdk.crypto.HashTokenGenerator;
import net.libyaguide.gpay.sdk.crypto.ResponseVerifier;
import net.libyaguide.gpay.sdk.crypto.VerificationHashGenerator;
import net.libyaguide.gpay.sdk.model.*;
import com.google.gson.Gson;
import okhttp3.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * GPayApiClient provides a client for interacting with the GPay Payment API.
 * <p>
 * This client handles authentication, request signing, and response verification for all supported endpoints.
 * <p>
 * <b>Authentication:</b>
 * <ul>
 *   <li>API Key: Used as a Bearer token in the Authorization header.</li>
 *   <li>Secret Key: Used for HMAC-SHA256 signing of requests and responses.</li>
 *   <li>Password: Used for hash token generation with a random salt.</li>
 * </ul>
 *
 * <b>Request Signing:</b>
 * <ul>
 *   <li>Each request includes a random salt and a verification hash in the headers.</li>
 *   <li>Parameters are sorted and concatenated to form the string to sign.</li>
 *   <li>Signing uses HMAC-SHA256 with the secret key.</li>
 * </ul>
 *
 * <b>Response Verification:</b>
 * <ul>
 *   <li>Each response is verified using the salt and hash from the response headers.</li>
 *   <li>Throws SecurityException if verification fails.</li>
 * </ul>
 *
 * <b>Usage Example:</b>
 * <pre>
 *   GPayApiClient client = new GPayApiClient(apiKey, secretKey, password, baseUrl);
 *   Balance balance = client.getWalletBalance("en");
 * </pre>
 *
 * <b>Endpoints:</b>
 * <ul>
 *   <li>getWalletBalance - Retrieve wallet balance</li>
 *   <li>createPaymentRequest - Create a payment request</li>
 *   <li>checkPaymentStatus - Check payment status</li>
 *   <li>sendMoney - Send money to another wallet</li>
 *   <li>getStatement - Get day statement</li>
 *   <li>checkWallet - Check wallet existence/details</li>
 *   <li>getOutstandingTransactions - Get outstanding transactions</li>
 * </ul>
 */
public class GPayApiClient {
    private final String apiKey;
    private final String secretKey;
    private final String password;
    private final String baseUrl;
    private final OkHttpClient httpClient;
    private final Gson gson;
    private final String language;

    /**
     * Enum for GPay API base URLs.
     */
    public enum BaseUrl {
        /**
         * Staging URL for GPay API.
         * Used for testing and development.
         */
        STAGING("https://gpay-staging.libyaguide.net/banking/api/onlinewallet/v1"),
        /**
         * Production URL for GPay API.
         * Use this for live transactions.
         */
        PRODUCTION("https://gpay.ly/banking/api/onlinewallet/v1");

        /** The base URL as a string. */
        private final String url;
        /**
         * Constructs a BaseUrl enum with the specified URL.
         * @param url The base URL for the enum.
         */
        BaseUrl(String url) { this.url = url; }
        /**
         * Gets the URL for the base URL enum.
         * @return The base URL as a string.
         */
        public String getUrl() { return url; }
    }

    /**
     * @param apiKey The API key for authentication.
     * @param secretKey The secret key for signing requests.
     * @param password The password for hash token generation.
     * @param baseUrl The base URL enum value (BaseUrl.STAGING or BaseUrl.PRODUCTION).
     * @param language The language for the response (default: 'en').
     */
    public GPayApiClient(String apiKey, String secretKey, String password, BaseUrl baseUrl, String language) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.password = password;
        this.baseUrl = baseUrl.getUrl();
        this.language = (language == null || language.isEmpty()) ? "en" : language;
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        this.gson = new Gson();
    }

    /**
     * Constructor with default language 'en'.
     * @param apiKey The API key for authentication.
     * @param secretKey The secret key for signing requests.
     * @param password The password for hash token generation.
     * @param baseUrl The base URL enum value (BaseUrl.STAGING or BaseUrl.PRODUCTION).
     */
    public GPayApiClient(String apiKey, String secretKey, String password, BaseUrl baseUrl) {
        this(apiKey, secretKey, password, baseUrl, "en");
    }

    private Headers buildHeaders(String salt, String verificationHash, String language) {
        return new Headers.Builder()
                .add("Authorization", "Bearer " + apiKey)
                .add("Accept-Language", language)
                .add("X-Signature-Salt", salt)
                .add("X-Signature-Hash", verificationHash)
                .build();
    }

    private ApiResponse sendRequest(String endpoint, Map<String, String> params) throws Exception {
        String salt = HashTokenGenerator.generateSalt();
        String hashToken = HashTokenGenerator.generateHashToken(salt, password);
        String verificationHash = VerificationHashGenerator.generateVerificationHash(
            hashToken, params, secretKey
        );
        Headers headers = buildHeaders(salt, verificationHash, language);
        String jsonBody = gson.toJson(params);
        RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(baseUrl + endpoint)
                .headers(headers)
                .post(body)
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("HTTP error: " + response.code() + " - " + response.message());
            }
            Map<String, String> headerMap = new HashMap<>();
            response.headers().toMultimap().forEach((k, v) -> headerMap.put(k.toLowerCase(), String.join(",", v)));
            return new ApiResponse(
                response.body().string(),
                headerMap,
                response.code()
            );
        }
    }

    /**
     * Retrieves the current wallet balance.
     * @return Balance object containing the current available balance and response timestamp.
     * @throws Exception if the request fails or response verification fails.
     */
    // Retrieve Wallet Balance
    public Balance getWalletBalance() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("request_timestamp", String.valueOf(System.currentTimeMillis()));
        ApiResponse apiResponse = sendRequest("/info/balance", params);
        com.google.gson.JsonObject data = gson.fromJson(apiResponse.response, com.google.gson.JsonObject.class).getAsJsonObject("data");
        // Response verification
        Map<String, String> verifyFields = new HashMap<>();
        verifyFields.put("balance", data.get("balance").getAsString());
        verifyFields.put("response_timestamp", data.get("response_timestamp").getAsString());
        if (!ResponseVerifier.verifyResponse(secretKey, password, apiResponse, verifyFields)) {
            throw new SecurityException("Response verification failed for getWalletBalance");
        }
        Balance balance = new Balance();
        balance.setBalance(new java.math.BigDecimal(data.get("balance").getAsString()));
        balance.setResponseTimestamp(new java.util.Date(Long.parseLong(data.get("response_timestamp").getAsString())));
        return balance;
    }

    /**
     * Creates a payment request for a specified amount.
     * @param amount The amount to request (as string, decimal value).
     * @param referenceNo Optional reference number (alphanumeric, spaces, underscores).
     * @param description Optional description (max 255 chars, restricted special chars).
     * @return PaymentRequest object with details of the created payment request.
     * @throws Exception if the request fails or response verification fails.
     */
    // Create Payment Request
    public PaymentRequest createPaymentRequest(BigDecimal amount, String referenceNo, String description) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("amount", amount.toString());
        params.put("reference_no", referenceNo == null ? "" : referenceNo);
        params.put("description", description == null ? "" : description);
        params.put("request_timestamp", String.valueOf(System.currentTimeMillis()));
        ApiResponse apiResponse = sendRequest("/payment/create-payment-request", params);
        com.google.gson.JsonObject data = gson.fromJson(apiResponse.response, com.google.gson.JsonObject.class).getAsJsonObject("data");
        // Response verification
        Map<String, String> verifyFields = new HashMap<>();
        verifyFields.put("requester_username", data.get("requester_username").getAsString());
        verifyFields.put("request_id", data.get("request_id").getAsString());
        verifyFields.put("request_time", data.get("request_time").getAsString());
        verifyFields.put("amount", data.get("amount").getAsString());
        verifyFields.put("reference_no", data.has("reference_no") && !data.get("reference_no").isJsonNull() ? data.get("reference_no").getAsString() : "");
        verifyFields.put("response_timestamp", data.get("response_timestamp").getAsString());
        if (!ResponseVerifier.verifyResponse(secretKey, password, apiResponse, verifyFields)) {
            throw new SecurityException("Response verification failed for createPaymentRequest");
        }
        PaymentRequest req = new PaymentRequest();
        req.setRequesterUsername(data.get("requester_username").getAsString());
        req.setRequestId(data.get("request_id").getAsString());
        req.setRequestTime(new java.util.Date(Long.parseLong(data.get("request_time").getAsString())));
        req.setAmount(new java.math.BigDecimal(data.get("amount").getAsString()));
        req.setReferenceNo(data.has("reference_no") && !data.get("reference_no").isJsonNull() ? data.get("reference_no").getAsString() : null);
        req.setResponseTimestamp(new java.util.Date(Long.parseLong(data.get("response_timestamp").getAsString())));
        return req;
    }

    /**
     * Checks the status of a payment request by its request ID.
     * @param requestId The payment request ID (UUID).
     * @return PaymentStatus object with the status of the payment request.
     * @throws Exception if the request fails or response verification fails.
     */
    // Check Payment Status
    public PaymentStatus checkPaymentStatus(String requestId) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("request_id", requestId);
        params.put("request_timestamp", String.valueOf(System.currentTimeMillis()));
        ApiResponse apiResponse = sendRequest("/payment/check-payment-status", params);
        com.google.gson.JsonObject data = gson.fromJson(apiResponse.response, com.google.gson.JsonObject.class).getAsJsonObject("data");
        // Response verification
        Map<String, String> verifyFields = new HashMap<>();
        verifyFields.put("request_id", data.get("request_id").getAsString());
        verifyFields.put("transaction_id", data.has("transaction_id") && !data.get("transaction_id").isJsonNull() ? data.get("transaction_id").getAsString() : "");
        verifyFields.put("amount", data.get("amount").getAsString());
        verifyFields.put("payment_timestamp", data.has("payment_timestamp") && !data.get("payment_timestamp").isJsonNull() ? data.get("payment_timestamp").getAsString() : "");
        verifyFields.put("reference_no", data.has("reference_no") && !data.get("reference_no").isJsonNull() ? data.get("reference_no").getAsString() : "");
        verifyFields.put("description", data.has("description") && !data.get("description").isJsonNull() ? data.get("description").getAsString() : "");
        verifyFields.put("is_paid", data.has("is_paid") && !data.get("is_paid").isJsonNull() ? String.valueOf(data.get("is_paid").getAsBoolean()) : "");
        verifyFields.put("response_timestamp", data.get("response_timestamp").getAsString());
        if (!ResponseVerifier.verifyResponse(secretKey, password, apiResponse, verifyFields)) {
            throw new SecurityException("Response verification failed for checkPaymentStatus");
        }
        PaymentStatus status = new PaymentStatus();
        status.setRequestId(data.get("request_id").getAsString());
        status.setTransactionId(data.has("transaction_id") && !data.get("transaction_id").isJsonNull() ? data.get("transaction_id").getAsString() : null);
        status.setAmount(new java.math.BigDecimal(data.get("amount").getAsString()));
        status.setPaymentTimestamp(data.has("payment_timestamp") && !data.get("payment_timestamp").isJsonNull() ? new java.util.Date(Long.parseLong(data.get("payment_timestamp").getAsString())) : null);
        status.setReferenceNo(data.has("reference_no") && !data.get("reference_no").isJsonNull() ? data.get("reference_no").getAsString() : null);
        status.setDescription(data.has("description") && !data.get("description").isJsonNull() ? data.get("description").getAsString() : null);
        status.setPaid(data.get("is_paid").getAsBoolean());
        status.setResponseTimestamp(new java.util.Date(Long.parseLong(data.get("response_timestamp").getAsString())));
        return status;
    }

    /**
     * Sends money to another wallet.
     * @param amount The amount to send (as string, decimal value).
     * @param walletGatewayId The recipient's wallet gateway ID (UUID).
     * @param referenceNo Optional reference number.
     * @param description Optional description.
     * @return SendMoneyResult object with details of the transaction.
     * @throws Exception if the request fails or response verification fails.
     */
    // Send Money
    public SendMoneyResult sendMoney(BigDecimal amount, String walletGatewayId, String referenceNo, String description) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("amount", amount.toString());
        params.put("wallet_gateway_id", walletGatewayId);
        params.put("reference_no", referenceNo == null ? "" : referenceNo);
        params.put("description", description == null ? "" : description);
        params.put("request_timestamp", String.valueOf(System.currentTimeMillis()));
        ApiResponse apiResponse = sendRequest("/payment/send-money", params);
        com.google.gson.JsonObject data = gson.fromJson(apiResponse.response, com.google.gson.JsonObject.class).getAsJsonObject("data");
        // Response verification
        Map<String, String> verifyFields = new HashMap<>();
        verifyFields.put("amount", data.get("amount").getAsString());
        verifyFields.put("sender_fee", data.get("sender_fee").getAsString());
        verifyFields.put("transaction_id", data.get("transaction_id").getAsString());
        verifyFields.put("old_balance", data.get("old_balance").getAsString());
        verifyFields.put("new_balance", data.get("new_balance").getAsString());
        verifyFields.put("timestamp", data.get("timestamp").getAsString());
        verifyFields.put("reference_no", data.has("reference_no") && !data.get("reference_no").isJsonNull() ? data.get("reference_no").getAsString() : "");
        verifyFields.put("response_timestamp", data.get("response_timestamp").getAsString());
        if (!ResponseVerifier.verifyResponse(secretKey, password, apiResponse, verifyFields)) {
            throw new SecurityException("Response verification failed for sendMoney");
        }
        SendMoneyResult result = new SendMoneyResult();
        result.setAmount(new java.math.BigDecimal(data.get("amount").getAsString()));
        result.setSenderFee(new java.math.BigDecimal(data.get("sender_fee").getAsString()));
        result.setTransactionId(data.get("transaction_id").getAsString());
        result.setOldBalance(new java.math.BigDecimal(data.get("old_balance").getAsString()));
        result.setNewBalance(new java.math.BigDecimal(data.get("new_balance").getAsString()));
        result.setTimestamp(new java.util.Date(Long.parseLong(data.get("timestamp").getAsString())));
        result.setReferenceNo(data.has("reference_no") && !data.get("reference_no").isJsonNull() ? data.get("reference_no").getAsString() : null);
        result.setResponseTimestamp(new java.util.Date(Long.parseLong(data.get("response_timestamp").getAsString())));
        return result;
    }

    /**
     * Retrieves the wallet's transaction statement for a specific day.
     * @param date The date in YYYY-MM-DD format.
     * @return Statement object containing the day's transactions and balances.
     * @throws Exception if the request fails or response verification fails.
     */
    // Get Day Statement
    public Statement getStatement(String date) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("date", date);
        params.put("request_timestamp", String.valueOf(System.currentTimeMillis()));
        ApiResponse apiResponse = sendRequest("/info/statement", params);
        com.google.gson.JsonObject data = gson.fromJson(apiResponse.response, com.google.gson.JsonObject.class).getAsJsonObject("data");
        // Response verification
        Map<String, String> verifyFields = new HashMap<>();
        verifyFields.put("available_balance", data.get("available_balance").getAsString());
        verifyFields.put("outstanding_credit", data.get("outstanding_credit").getAsString());
        verifyFields.put("outstanding_debit", data.get("outstanding_debit").getAsString());
        verifyFields.put("day_balance", data.get("day_balance").getAsString());
        verifyFields.put("day_total_in", data.get("day_total_in").getAsString());
        verifyFields.put("day_total_out", data.get("day_total_out").getAsString());
        verifyFields.put("response_timestamp", data.get("response_timestamp").getAsString());
        if (!ResponseVerifier.verifyResponse(secretKey, password, apiResponse, verifyFields)) {
            throw new SecurityException("Response verification failed for getStatement");
        }
        Statement statement = new Statement();
        statement.setAvailableBalance(new java.math.BigDecimal(data.get("available_balance").getAsString()));
        statement.setOutstandingCredit(new java.math.BigDecimal(data.get("outstanding_credit").getAsString()));
        statement.setOutstandingDebit(new java.math.BigDecimal(data.get("outstanding_debit").getAsString()));
        statement.setDayBalance(new java.math.BigDecimal(data.get("day_balance").getAsString()));
        statement.setDayTotalIn(new java.math.BigDecimal(data.get("day_total_in").getAsString()));
        statement.setDayTotalOut(new java.math.BigDecimal(data.get("day_total_out").getAsString()));
        statement.setResponseTimestamp(new java.util.Date(Long.parseLong(data.get("response_timestamp").getAsString())));
        java.util.List<StatementTransaction> txs = new java.util.ArrayList<>();
        if (data.has("day_statement") && data.get("day_statement").isJsonArray()) {
            for (com.google.gson.JsonElement el : data.getAsJsonArray("day_statement")) {
                com.google.gson.JsonObject tx = el.getAsJsonObject();
                StatementTransaction stx = new StatementTransaction();
                stx.setTransactionId(tx.get("transaction_id").getAsString());
                stx.setDatetime(tx.get("datetime").getAsString());
                stx.setTimestamp(tx.has("timestamp") && !tx.get("timestamp").isJsonNull() ? new java.util.Date(Long.parseLong(tx.get("timestamp").getAsString())) : null);
                stx.setDescription(tx.has("description") && !tx.get("description").isJsonNull() ? tx.get("description").getAsString() : null);
                stx.setAmount(tx.has("amount") && !tx.get("amount").isJsonNull() ? new java.math.BigDecimal(tx.get("amount").getAsString()) : null);
                stx.setBalance(tx.has("balance") && !tx.get("balance").isJsonNull() ? new java.math.BigDecimal(tx.get("balance").getAsString()) : null);
                stx.setReferenceNo(tx.has("reference_no") && !tx.get("reference_no").isJsonNull() ? tx.get("reference_no").getAsString() : null);
                stx.setOpTypeId(tx.has("op_type_id") && !tx.get("op_type_id").isJsonNull() ? OperationType.fromValue(tx.get("op_type_id").getAsInt()) : null);
                stx.setStatus(tx.has("status") && !tx.get("status").isJsonNull() ? TransactionStatus.fromValue(tx.get("status").getAsInt()) : null);
                stx.setCreatedAt(tx.has("created_at") && !tx.get("created_at").isJsonNull() ? new java.util.Date(Long.parseLong(tx.get("created_at").getAsString())) : null);
                txs.add(stx);
            }
        }
        statement.setDayStatement(txs);
        return statement;
    }

    /**
     * Checks if a wallet exists and retrieves its details.
     * @param walletGatewayId The wallet gateway ID to check (UUID).
     * @return WalletCheck object with wallet details.
     * @throws Exception if the request fails or response verification fails.
     */
    // Check Wallet
    public WalletCheck checkWallet(String walletGatewayId) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("wallet_gateway_id", walletGatewayId);
        params.put("request_timestamp", String.valueOf(System.currentTimeMillis()));
        ApiResponse apiResponse = sendRequest("/info/check-wallet", params);
        com.google.gson.JsonObject data = gson.fromJson(apiResponse.response, com.google.gson.JsonObject.class).getAsJsonObject("data");
        // Response verification
        Map<String, String> verifyFields = new HashMap<>();
        verifyFields.put("exists", String.valueOf(data.get("exists").getAsBoolean()));
        verifyFields.put("wallet_gateway_id", data.get("wallet_gateway_id").getAsString());
        verifyFields.put("wallet_name", data.has("wallet_name") && !data.get("wallet_name").isJsonNull() ? data.get("wallet_name").getAsString() : "");
        verifyFields.put("user_account_name", data.has("user_account_name") && !data.get("user_account_name").isJsonNull() ? data.get("user_account_name").getAsString() : "");
        verifyFields.put("can_receive_money", String.valueOf(data.get("can_receive_money").getAsBoolean()));
        verifyFields.put("response_timestamp", data.get("response_timestamp").getAsString());
        if (!ResponseVerifier.verifyResponse(secretKey, password, apiResponse, verifyFields)) {
            throw new SecurityException("Response verification failed for checkWallet");
        }
        WalletCheck check = new WalletCheck();
        check.setExists(data.get("exists").getAsBoolean());
        check.setWalletGatewayId(data.get("wallet_gateway_id").getAsString());
        check.setWalletName(data.has("wallet_name") && !data.get("wallet_name").isJsonNull() ? data.get("wallet_name").getAsString() : null);
        check.setUserAccountName(data.has("user_account_name") && !data.get("user_account_name").isJsonNull() ? data.get("user_account_name").getAsString() : null);
        check.setCanReceiveMoney(data.get("can_receive_money").getAsBoolean());
        check.setResponseTimestamp(new java.util.Date(Long.parseLong(data.get("response_timestamp").getAsString())));
        return check;
    }

    /**
     * Retrieves a list of outstanding transactions.
     * @return OutstandingTransactions object containing outstanding credits, debits, and transactions.
     * @throws Exception if the request fails or response verification fails.
     */
    // Get Outstanding Transactions
    public OutstandingTransactions getOutstandingTransactions() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("request_timestamp", String.valueOf(System.currentTimeMillis()));
        ApiResponse apiResponse = sendRequest("/info/outstanding-transactions", params);
        com.google.gson.JsonObject data = gson.fromJson(apiResponse.response, com.google.gson.JsonObject.class).getAsJsonObject("data");
        // Response verification
        Map<String, String> verifyFields = new HashMap<>();
        verifyFields.put("outstanding_credit", data.get("outstanding_credit").getAsString());
        verifyFields.put("outstanding_debit", data.get("outstanding_debit").getAsString());
        verifyFields.put("response_timestamp", data.get("response_timestamp").getAsString());
        if (!ResponseVerifier.verifyResponse(secretKey, password, apiResponse, verifyFields)) {
            throw new SecurityException("Response verification failed for getOutstandingTransactions");
        }
        OutstandingTransactions outstanding = new OutstandingTransactions();
        outstanding.setOutstandingCredit(new java.math.BigDecimal(data.get("outstanding_credit").getAsString()));
        outstanding.setOutstandingDebit(new java.math.BigDecimal(data.get("outstanding_debit").getAsString()));
        outstanding.setResponseTimestamp(new java.util.Date(Long.parseLong(data.get("response_timestamp").getAsString())));
        java.util.List<OutstandingTransaction> txs = new java.util.ArrayList<>();
        if (data.has("outstanding_transactions") && data.get("outstanding_transactions").isJsonArray()) {
            for (com.google.gson.JsonElement el : data.getAsJsonArray("outstanding_transactions")) {
                com.google.gson.JsonObject tx = el.getAsJsonObject();
                OutstandingTransaction otx = new OutstandingTransaction();
                otx.setTransactionId(tx.get("transaction_id").getAsString());
                otx.setDatetime(tx.get("datetime").getAsString());
                otx.setTimestamp(tx.has("timestamp") && !tx.get("timestamp").isJsonNull() ? new java.util.Date(Long.parseLong(tx.get("timestamp").getAsString())) : null);
                otx.setDescription(tx.has("description") && !tx.get("description").isJsonNull() ? tx.get("description").getAsString() : null);
                otx.setAmount(tx.has("amount") && !tx.get("amount").isJsonNull() ? new java.math.BigDecimal(tx.get("amount").getAsString()) : null);
                otx.setBalance(tx.has("balance") && !tx.get("balance").isJsonNull() ? new java.math.BigDecimal(tx.get("balance").getAsString()) : null);
                otx.setReferenceNo(tx.has("reference_no") && !tx.get("reference_no").isJsonNull() ? tx.get("reference_no").getAsString() : null);
                otx.setOpTypeId(tx.has("op_type_id") && !tx.get("op_type_id").isJsonNull() ? OperationType.fromValue(tx.get("op_type_id").getAsInt()) : null);
                otx.setStatus(tx.has("status") && !tx.get("status").isJsonNull() ? TransactionStatus.fromValue(tx.get("status").getAsInt()) : null);
                otx.setCreatedAt(tx.has("created_at") && !tx.get("created_at").isJsonNull() ? new java.util.Date(Long.parseLong(tx.get("created_at").getAsString())) : null);
                txs.add(otx);
            }
        }
        outstanding.setOutstandingTransactions(txs);
        return outstanding;
    }




    /**
     * Represents the API response including body, headers, and status code.
     */
    public class ApiResponse {
        /** The response body as a string. */
        public final String response;
        /** The response headers as a map. */
        public final Map<String,String> headers;
        /** The HTTP status code. */
        public final int code;
        /**
         * Constructs an ApiResponse.
         * @param response The response body.
         * @param headers The response headers.
         * @param code The HTTP status code.
         */
        private ApiResponse(String response, Map<String, String> headers, int code) {
            this.response = response;
            this.headers = headers;
            this.code = code;
        }
        /**
         * Parses the response body as a JsonObject.
         * @return The response as a JsonObject.
         */
        public com.google.gson.JsonObject getJsonResponse() {
            return gson.fromJson(response, com.google.gson.JsonObject.class);
        }
        /**
         * Returns the response body as a string.
         * @return The response body.
         */
        @Override
        public String toString() {
            return this.response;
        }
    }
}
