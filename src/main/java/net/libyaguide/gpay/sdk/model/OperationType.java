package net.libyaguide.gpay.sdk.model;

/**
 * Enum representing the types of operations in the GPay system.
 */
public enum OperationType {
    /** Direct transfer operation. */
    DIRECT_TRANSFER(1),
    /** Payment request operation. */
    PAYMENT_REQUEST(2),
    /** Bank deposit operation. */
    BANK_DEPOSIT(3),
    /** Bank withdrawal operation. */
    BANK_WITHDRAW(4),
    /** Transaction fee operation. */
    TRANSACTION_FEE(5),
    /** Local transfer operation. */
    LOCAL_TRANSFER(6);

    private final int value;

    OperationType(int value) { this.value = value; }
    /** Gets the integer value of the operation type. 
     * @return the integer value of the operation type.
    */
    public int getValue() { return value; }

    /** Returns the OperationType for the given value. 
     * @param value the integer value of the operation type.
     * @return the corresponding OperationType.
     * @throws IllegalArgumentException if the value does not match any OperationType.
    */
    public static OperationType fromValue(int value) {
        for (OperationType o : values()) {
            if (o.value == value) return o;
        }
        throw new IllegalArgumentException("Unknown OperationType value: " + value);
    }
}
