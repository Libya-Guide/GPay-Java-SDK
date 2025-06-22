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
    /** Gets the amount sent. */
    public BigDecimal getAmount() { return amount; }
    /** Sets the amount sent. */
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    /** Gets the fee charged to the sender. */
    public BigDecimal getSenderFee() { return senderFee; }
    /** Sets the fee charged to the sender. */
    public void setSenderFee(BigDecimal senderFee) { this.senderFee = senderFee; }
    /** Gets the unique ID for the transaction. */
    public String getTransactionId() { return transactionId; }
    /** Sets the unique ID for the transaction. */
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    /** Gets the balance before the transaction. */
    public BigDecimal getOldBalance() { return oldBalance; }
    /** Sets the balance before the transaction. */
    public void setOldBalance(BigDecimal oldBalance) { this.oldBalance = oldBalance; }
    /** Gets the balance after the transaction. */
    public BigDecimal getNewBalance() { return newBalance; }
    /** Sets the balance after the transaction. */
    public void setNewBalance(BigDecimal newBalance) { this.newBalance = newBalance; }
    /** Gets the timestamp of the transaction. */
    public Date getTimestamp() { return timestamp; }
    /** Sets the timestamp of the transaction. */
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    /** Gets the reference number provided in the request, if any. */
    public String getReferenceNo() { return referenceNo; }
    /** Sets the reference number provided in the request, if any. */
    public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }
    /** Gets the response timestamp. */
    public Date getResponseTimestamp() { return responseTimestamp; }
    /** Sets the response timestamp. */
    public void setResponseTimestamp(Date responseTimestamp) { this.responseTimestamp = responseTimestamp; }
}
