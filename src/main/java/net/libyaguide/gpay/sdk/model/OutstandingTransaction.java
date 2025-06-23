package net.libyaguide.gpay.sdk.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a single outstanding transaction in the GPay system.
 */
public class OutstandingTransaction {
    private String transactionId;
    private String datetime;
    private Date timestamp;
    private String description;
    private BigDecimal amount;
    private BigDecimal balance;
    private String referenceNo;
    private OperationType opTypeId;
    private TransactionStatus status;
    private Date createdAt;

    // Getters and setters
    /** Gets the transaction ID. 
     * @return the transaction ID.
    */
    public String getTransactionId() { return transactionId; }
    /** Sets the transaction ID. 
     * @param transactionId the transaction ID to set.
    */
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    /** Gets the datetime string. 
     * @return the datetime string in ISO 8601 format.
    */
    public String getDatetime() { return datetime; }
    /** Sets the datetime string. 
     * @param datetime the datetime string in ISO 8601 format.
    */
    public void setDatetime(String datetime) { this.datetime = datetime; }
    /** Gets the timestamp. 
     * @return the timestamp as a Date object.
    */
    public Date getTimestamp() { return timestamp; }
    /** Sets the timestamp. 
     * @param timestamp the timestamp to set.
    */
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    /** Gets the description. 
     * @return the description of the transaction.
    */
    public String getDescription() { return description; }
    /** Sets the description. 
     * @param description the description of the transaction.
    */
    public void setDescription(String description) { this.description = description; }
    /** Gets the amount. 
     * @return the transaction amount as a BigDecimal.
    */
    public BigDecimal getAmount() { return amount; }
    /** Sets the amount. 
     * @param amount the transaction amount to set.
    */
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    /** Gets the balance. 
     * @return the balance after the transaction as a BigDecimal.
    */
    public BigDecimal getBalance() { return balance; }
    /** Sets the balance. 
     * @param balance the balance to set.
    */
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    /** Gets the reference number. 
     * @return the reference number associated with the transaction.
    */
    public String getReferenceNo() { return referenceNo; }
    /** Sets the reference number. 
     * @param referenceNo the reference number to set.
    */
    public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }
    /** Gets the operation type. 
     * @return the operation type as an OperationType enum.
    */
    public OperationType getOpTypeId() { return opTypeId; }
    /** Sets the operation type. 
     * @param opTypeId the operation type to set as an OperationType enum.
    */
    public void setOpTypeId(OperationType opTypeId) { this.opTypeId = opTypeId; }
    /** Gets the transaction status. 
     * @return the transaction status as a TransactionStatus enum.
    */
    public TransactionStatus getStatus() { return status; }
    /** Sets the transaction status. 
     * @param status the transaction status to set as a TransactionStatus enum.
    */
    public void setStatus(TransactionStatus status) { this.status = status; }
    /** Gets the creation date. 
     * @return the creation date as a Date object.
    */
    public Date getCreatedAt() { return createdAt; }
    /** Sets the creation date. 
     * @param createdAt the creation date to set as a Date object.
    */
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
