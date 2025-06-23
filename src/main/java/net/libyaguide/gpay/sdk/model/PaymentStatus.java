package net.libyaguide.gpay.sdk.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents the status of a payment request.
 * Contains details about the payment status, including whether it is paid.
 */
public class PaymentStatus {
    /** The unique ID of the payment request. */
    private String requestId;
    /** The transaction ID if payment is completed. */
    private String transactionId;
    /** The requested amount. */
    private BigDecimal amount;
    /** The payment timestamp if completed. */
    private Date paymentTimestamp;
    /** The reference number provided in the request, if any. */
    private String referenceNo;
    /** The description provided in the request. */
    private String description;
    /** Indicates whether the payment is completed. */
    private boolean isPaid;
    /** The response timestamp as a Date object. */
    private Date responseTimestamp;

    // Getters and setters
    /**
     * Gets the unique ID of the payment request.
     * @return the unique ID of the payment request
     */
    public String getRequestId() { return requestId; }
    /**
     * Sets the unique ID of the payment request.
     * @param requestId the unique ID of the payment request
     */
    public void setRequestId(String requestId) { this.requestId = requestId; }
    /**
     * Gets the transaction ID if payment is completed.
     * @return the transaction ID
     */
    public String getTransactionId() { return transactionId; }
    /**
     * Sets the transaction ID if payment is completed.
     * @param transactionId the transaction ID
     */
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    /**
     * Gets the requested amount.
     * @return the requested amount
     */
    public BigDecimal getAmount() { return amount; }
    /**
     * Sets the requested amount.
     * @param amount the requested amount
     */
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    /**
     * Gets the payment timestamp if completed.
     * @return the payment timestamp
     */
    public Date getPaymentTimestamp() { return paymentTimestamp; }
    /**
     * Sets the payment timestamp if completed.
     * @param paymentTimestamp the payment timestamp
     */
    public void setPaymentTimestamp(Date paymentTimestamp) { this.paymentTimestamp = paymentTimestamp; }
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
     * Gets the description provided in the request.
     * @return the description
     */
    public String getDescription() { return description; }
    /**
     * Sets the description provided in the request.
     * @param description the description
     */
    public void setDescription(String description) { this.description = description; }
    /**
     * Gets whether the payment is completed.
     * @return true if paid, false otherwise
     */
    public boolean isPaid() { return isPaid; }
    /**
     * Sets whether the payment is completed.
     * @param paid true if paid, false otherwise
     */
    public void setPaid(boolean paid) { isPaid = paid; }
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
