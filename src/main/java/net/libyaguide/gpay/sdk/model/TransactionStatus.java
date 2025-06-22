package net.libyaguide.gpay.sdk.model;

public enum TransactionStatus {
    PENDING(0),
    COMPLETED(1),
    APPLIED(2);

    private final int value;
    TransactionStatus(int value) { this.value = value; }
    public int getValue() { return value; }
    public static TransactionStatus fromValue(int value) {
        for (TransactionStatus s : values()) {
            if (s.value == value) return s;
        }
        throw new IllegalArgumentException("Unknown TransactionStatus value: " + value);
    }
}
