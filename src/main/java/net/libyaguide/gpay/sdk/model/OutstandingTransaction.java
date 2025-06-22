package net.libyaguide.gpay.sdk.model;

import java.math.BigDecimal;
import java.util.Date;

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
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getDatetime() { return datetime; }
    public void setDatetime(String datetime) { this.datetime = datetime; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public String getReferenceNo() { return referenceNo; }
    public void setReferenceNo(String referenceNo) { this.referenceNo = referenceNo; }
    public OperationType getOpTypeId() { return opTypeId; }
    public void setOpTypeId(OperationType opTypeId) { this.opTypeId = opTypeId; }
    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
