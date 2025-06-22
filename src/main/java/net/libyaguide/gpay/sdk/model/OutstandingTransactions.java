package net.libyaguide.gpay.sdk.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Represents a list of outstanding transactions.
 * Contains outstanding credits, debits, and the list of transactions.
 */
public class OutstandingTransactions {
    /** The total outstanding credit. */
    private BigDecimal outstandingCredit;
    /** The total outstanding debit. */
    private BigDecimal outstandingDebit;
    /** The response timestamp as a Date object. */
    private Date responseTimestamp;
    /** The list of outstanding transactions. */
    private List<OutstandingTransaction> outstandingTransactions;

    // Getters and setters
    /** Gets the total outstanding credit. */
    public BigDecimal getOutstandingCredit() { return outstandingCredit; }
    /** Sets the total outstanding credit. */
    public void setOutstandingCredit(BigDecimal outstandingCredit) { this.outstandingCredit = outstandingCredit; }
    /** Gets the total outstanding debit. */
    public BigDecimal getOutstandingDebit() { return outstandingDebit; }
    /** Sets the total outstanding debit. */
    public void setOutstandingDebit(BigDecimal outstandingDebit) { this.outstandingDebit = outstandingDebit; }
    /** Gets the response timestamp. */
    public Date getResponseTimestamp() { return responseTimestamp; }
    /** Sets the response timestamp. */
    public void setResponseTimestamp(Date responseTimestamp) { this.responseTimestamp = responseTimestamp; }
    /** Gets the list of outstanding transactions. */
    public List<OutstandingTransaction> getOutstandingTransactions() { return outstandingTransactions; }
    /** Sets the list of outstanding transactions. */
    public void setOutstandingTransactions(List<OutstandingTransaction> outstandingTransactions) { this.outstandingTransactions = outstandingTransactions; }
}
