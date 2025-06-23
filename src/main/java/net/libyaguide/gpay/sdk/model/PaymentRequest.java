package net.libyaguide.gpay.sdk.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents a payment request response.
 * Contains details of the created payment request.
 */
public class PaymentRequest {
    /** The username of the requester. */
    private String requesterUsername;
    /** The unique ID for the payment request. */
    private String requestId;
    /** The timestamp of the request. */
    private Date requestTime;
    /** The amount requested. */
    private BigDecimal amount;
    /** The reference number provided in the request, if any. */
    private String referenceNo;
    /** The response timestamp as a Date object. */
    private Date responseTimestamp;

    // Getters and setters
    /**
     * Gets the username of the requester.
     * @return the username of the requester
     */
    public String getRequesterUsername() { return requesterUsername; }
    /**
     * Sets the username of the requester.
     * @param requesterUsername the username of the requester
     */
    public void setRequesterUsername(String requesterUsername) { this.requesterUsername = requesterUsername; }
    /**
     * Gets the unique ID for the payment request.
     * @return the unique ID for the payment request
     */
    public String getRequestId() { return requestId; }
    /**
     * Sets the unique ID for the payment request.
     * @param requestId the unique ID for the payment request
     */
    public void setRequestId(String requestId) { this.requestId = requestId; }
    /**
     * Gets the timestamp of the request.
     * @return the timestamp of the request
     */
    public Date getRequestTime() { return requestTime; }
    /**
     * Sets the timestamp of the request.
     * @param requestTime the timestamp of the request
     */
    public void setRequestTime(Date requestTime) { this.requestTime = requestTime; }
    /**
     * Gets the amount requested.
     * @return the amount requested
     */
    public BigDecimal getAmount() { return amount; }
    /**
     * Sets the amount requested.
     * @param amount the amount requested
     */
    public void setAmount(BigDecimal amount) { this.amount = amount; }
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
