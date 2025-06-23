package net.libyaguide.gpay.sdk.model;

/**
 * Enum representing the status of a transaction in the GPay system.
 */
public enum TransactionStatus {
    /** Transaction is pending. */
    PENDING(0),
    /** Transaction is completed. */
    COMPLETED(1),
    /** Transaction is applied. */
    APPLIED(2);

    private final int value;
    TransactionStatus(int value) { this.value = value; }
    /**
     * Gets the integer value of the transaction status.
     * @return the integer value of the status
     */
    public int getValue() { return value; }
    /**
     * Returns the TransactionStatus for the given value.
     * @param value the integer value
     * @return the TransactionStatus
     */
    public static TransactionStatus fromValue(int value) {
        for (TransactionStatus s : values()) {
            if (s.value == value) return s;
        }
        throw new IllegalArgumentException("Unknown TransactionStatus value: " + value);
    }
}
