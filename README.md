# GPay API Java Client

This is a Java SDK for the GPay Payment API. It implements all authentication, request signing, and endpoint logic as described in the official API documentation.

## Features
- Secure API authentication and request signing (HMAC-SHA256)
- All documented endpoints implemented
- HTTP requests via OkHttp
- JSON serialization/deserialization via Gson

## Usage
Add this library as a dependency to your Maven project, configure your API credentials, and use the client to interact with the GPay API.

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

## Building
```
mvn clean install
```

## License
MIT
