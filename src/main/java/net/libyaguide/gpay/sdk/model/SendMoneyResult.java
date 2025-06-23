package net.libyaguide.gpay.sdk.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents the result of a send money operation.
 * Contains details of the transaction, including fees and balances.
 */
public class SendMoneyResult {
    /** The amount sent. */
    private BigDecimal amount;
    /** The fee charged to the sender. */
    private BigDecimal senderFee;
    /** The unique ID for the transaction. */
    private String transactionId;
    /** The balance before the transaction. */
    private BigDecimal oldBalance;
    /** The balance after the transaction. */
    private BigDecimal newBalance;
    /** The timestamp of the transaction. */
    private Date timestamp;
    /** The reference number provided in the request, if any. */
    private String referenceNo;
    /** The response timestamp as a Date object. */
    private Date responseTimestamp;

    // Getters and setters
    /**
     * Gets the amount sent.
     * @return the amount sent
     */
    public BigDecimal getAmount() { return amount; }
    /**
     * Sets the amount sent.
     * @param amount the amount sent
     */
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    /**
     * Gets the fee charged to the sender.
     * @return the sender fee
     */
    public BigDecimal getSenderFee() { return senderFee; }
    /**
     * Sets the fee charged to the sender.
     * @param senderFee the sender fee
     */
    public void setSenderFee(BigDecimal senderFee) { this.senderFee = senderFee; }
    /**
     * Gets the unique ID for the transaction.
     * @return the transaction ID
     */
    public String getTransactionId() { return transactionId; }
    /**
     * Sets the unique ID for the transaction.
     * @param transactionId the transaction ID
     */
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    /**
     * Gets the balance before the transaction.
     * @return the old balance
     */
    public BigDecimal getOldBalance() { return oldBalance; }
    /**
     * Sets the balance before the transaction.
     * @param oldBalance the old balance
     */
    public void setOldBalance(BigDecimal oldBalance) { this.oldBalance = oldBalance; }
    /**
     * Gets the balance after the transaction.
     * @return the new balance
     */
    public BigDecimal getNewBalance() { return newBalance; }
    /**
     * Sets the balance after the transaction.
     * @param newBalance the new balance
     */
    public void setNewBalance(BigDecimal newBalance) { this.newBalance = newBalance; }
    /**
     * Gets the timestamp of the transaction.
     * @return the timestamp
     */
    public Date getTimestamp() { return timestamp; }
    /**
     * Sets the timestamp of the transaction.
     * @param timestamp the timestamp
     */
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    /**
     * Gets the reference number provided in the request, if any.
     * @return the reference number
     */
    public String getReferenceNo() { return referenceNo; }
    /**
     * Sets the reference number provided in the request, if any.
     * @param referenceNo the reference number
     */
    public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }
    /**
     * Gets the response timestamp.
     * @return the response timestamp
     */
    public Date getResponseTimestamp() { return responseTimestamp; }
    /**
     * Sets the response timestamp.
     * @param responseTimestamp the response timestamp
     */
    public void setResponseTimestamp(Date responseTimestamp) { this.responseTimestamp = responseTimestamp; }
}
