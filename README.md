# GPay API Java Client

This is a Java SDK for the GPay Payment API. It implements all authentication, request signing, and endpoint logic as described in the official API documentation.

**Official API Documentation:**
[https://gpay.ly/banking/doc/index.html](https://gpay.ly/banking/doc/index.html)

## Features
- Secure API authentication and request signing (HMAC-SHA256)
- All documented endpoints implemented
- HTTP requests via OkHttp
- JSON serialization/deserialization via Gson


## Endpoints Supported
- Retrieve Wallet Balance
- Create Payment Request
- Check Payment Status
- Send Money
- Get Day Statement
- Check Wallet
- Get Outstanding Transactions

## Requirements
- Java 8+
- Maven


## Installation (Maven)
Add the following dependency to your Maven `pom.xml`:

```xml
<dependency>
    <groupId>net.libyaguide</groupId>
    <artifactId>gpay-api-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage Examples

### 1. Create the GPayApiClient
```java
import net.libyaguide.gpay.sdk.client.GPayApiClient;

String apiKey = "YOUR_API_KEY";
String secretKey = "YOUR_SECRET_KEY";
String password = "YOUR_PASSWORD";
GPayApiClient.BaseUrl baseUrl = GPayApiClient.BaseUrl.PRODUCTION; // or STAGING
GPayApiClient client = new GPayApiClient(apiKey, secretKey, password, baseUrl);
```

Please refer to the official documentation mentioned above to learn how to acquire the `apiKey`, `secretKey`, and `password`.

### 2. Retrieve Wallet Balance
```java
import net.libyaguide.gpay.sdk.model.Balance;

Balance balance = client.getWalletBalance();
System.out.println("Balance: " + balance.getBalance());
```

### 3. Create Payment Request
```java
import net.libyaguide.gpay.sdk.model.PaymentRequest;

PaymentRequest request = client.createPaymentRequest(new BigDecimal("100.00"), "REF123", "Payment for order");
System.out.println("Request ID: " + request.getRequestId());
```

### 4. Check Payment Status
```java
import net.libyaguide.gpay.sdk.model.PaymentStatus;

PaymentStatus status = client.checkPaymentStatus("REQUEST_ID");
System.out.println("Is Paid: " + status.isPaid());
```

### 5. Send Money
```java
import net.libyaguide.gpay.sdk.model.SendMoneyResult;

SendMoneyResult result = client.sendMoney(new BigDecimal("50.00"), "WALLET_GATEWAY_ID", "REF456", "Send to friend");
System.out.println("Transaction ID: " + result.getTransactionId());
```

### 6. Get Day Statement
```java
import net.libyaguide.gpay.sdk.model.Statement;
import net.libyaguide.gpay.sdk.model.StatementTransaction;

Statement statement = client.getStatement("2025-06-22");
System.out.println("Day Balance: " + statement.getDayBalance());
for (StatementTransaction txn : statement.getDayStatement()) {
    System.out.println("Txn: " + txn.getTransactionId() + ", Amount: " + txn.getAmount());
}
```

### 7. Check Wallet
```java
import net.libyaguide.gpay.sdk.model.WalletCheck;

WalletCheck wallet = client.checkWallet("WALLET_GATEWAY_ID");
System.out.println("Wallet Exists: " + wallet.exists());
```

### 8. Get Outstanding Transactions
```java
import net.libyaguide.gpay.sdk.model.OutstandingTransactions;
import net.libyaguide.gpay.sdk.model.OutstandingTransaction;

OutstandingTransactions outstanding = client.getOutstandingTransactions();
System.out.println("Outstanding Credit: " + outstanding.getOutstandingCredit());
for (OutstandingTransaction txn : outstanding.getOutstandingTransactions()) {
    System.out.println("Txn: " + txn.getTransactionId() + ", Amount: " + txn.getAmount());
}
```


## License
MIT
