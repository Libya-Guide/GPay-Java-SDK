package net.libyaguide.gpay.sdk.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a single transaction in a wallet statement.
 */
public class StatementTransaction {
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

    /**
     * Gets the transaction ID.
     * @return the transaction ID
     */
    public String getTransactionId() { return transactionId; }
    /**
     * Sets the transaction ID.
     * @param transactionId the transaction ID
     */
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    /**
     * Gets the datetime string.
     * @return the datetime string
     */
    public String getDatetime() { return datetime; }
    /**
     * Sets the datetime string.
     * @param datetime the datetime string
     */
    public void setDatetime(String datetime) { this.datetime = datetime; }
    /**
     * Gets the timestamp.
     * @return the timestamp
     */
    public Date getTimestamp() { return timestamp; }
    /**
     * Sets the timestamp.
     * @param timestamp the timestamp
     */
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    /**
     * Gets the description.
     * @return the description
     */
    public String getDescription() { return description; }
    /**
     * Sets the description.
     * @param description the description
     */
    public void setDescription(String description) { this.description = description; }
    /**
     * Gets the amount.
     * @return the amount
     */
    public BigDecimal getAmount() { return amount; }
    /**
     * Sets the amount.
     * @param amount the amount
     */
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    /**
     * Gets the balance.
     * @return the balance
     */
    public BigDecimal getBalance() { return balance; }
    /**
     * Sets the balance.
     * @param balance the balance
     */
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    /**
     * Gets the reference number.
     * @return the reference number
     */
    public String getReferenceNo() { return referenceNo; }
    /**
     * Sets the reference number.
     * @param referenceNo the reference number
     */
    public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }
    /**
     * Gets the operation type.
     * @return the operation type
     */
    public OperationType getOpTypeId() { return opTypeId; }
    /**
     * Sets the operation type.
     * @param opTypeId the operation type
     */
    public void setOpTypeId(OperationType opTypeId) { this.opTypeId = opTypeId; }
    /**
     * Gets the transaction status.
     * @return the transaction status
     */
    public TransactionStatus getStatus() { return status; }
    /**
     * Sets the transaction status.
     * @param status the transaction status
     */
    public void setStatus(TransactionStatus status) { this.status = status; }
    /**
     * Gets the creation date.
     * @return the creation date
     */
    public Date getCreatedAt() { return createdAt; }
    /**
     * Sets the creation date.
     * @param createdAt the creation date
     */
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
