package net.libyaguide.gpay.sdk.model;

public enum OperationType {
    DIRECT_TRANSFER(1),
    PAYMENT_REQUEST(2),
    BANK_DEPOSIT(3),
    BANK_WITHDRAW(4),
    TRANSACTION_FEE(5),
    LOCAL_TRANSFER(6);

    private final int value;
    OperationType(int value) { this.value = value; }
    public int getValue() { return value; }
    public static OperationType fromValue(int value) {
        for (OperationType o : values()) {
            if (o.value == value) return o;
        }
        throw new IllegalArgumentException("Unknown OperationType value: " + value);
    }
}
