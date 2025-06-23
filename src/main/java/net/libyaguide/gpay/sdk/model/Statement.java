package net.libyaguide.gpay.sdk.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Represents a wallet statement for a specific day.
 * Contains balances and a list of transactions for the given day.
 */
public class Statement {
    /** The available balance at the time of the request. */
    private BigDecimal availableBalance;
    /** The total outstanding credit. */
    private BigDecimal outstandingCredit;
    /** The total outstanding debit. */
    private BigDecimal outstandingDebit;
    /** The balance at the end of the given day. */
    private BigDecimal dayBalance;
    /** The total credited on the given day. */
    private BigDecimal dayTotalIn;
    /** The total debited on the given day. */
    private BigDecimal dayTotalOut;
    /** The response timestamp as a Date object. */
    private Date responseTimestamp;
    /** The list of transactions for the given day. */
    private List<StatementTransaction> dayStatement;

    // Getters and setters
    /**
     * Gets the available balance at the time of the request.
     * @return the available balance
     */
    public BigDecimal getAvailableBalance() { return availableBalance; }
    /**
     * Sets the available balance at the time of the request.
     * @param availableBalance the available balance
     */
    public void setAvailableBalance(BigDecimal availableBalance) { this.availableBalance = availableBalance; }
    /**
     * Gets the total outstanding credit.
     * @return the total outstanding credit
     */
    public BigDecimal getOutstandingCredit() { return outstandingCredit; }
    /**
     * Sets the total outstanding credit.
     * @param outstandingCredit the total outstanding credit
     */
    public void setOutstandingCredit(BigDecimal outstandingCredit) { this.outstandingCredit = outstandingCredit; }
    /**
     * Gets the total outstanding debit.
     * @return the total outstanding debit
     */
    public BigDecimal getOutstandingDebit() { return outstandingDebit; }
    /**
     * Sets the total outstanding debit.
     * @param outstandingDebit the total outstanding debit
     */
    public void setOutstandingDebit(BigDecimal outstandingDebit) { this.outstandingDebit = outstandingDebit; }
    /**
     * Gets the balance at the end of the given day.
     * @return the day balance
     */
    public BigDecimal getDayBalance() { return dayBalance; }
    /**
     * Sets the balance at the end of the given day.
     * @param dayBalance the day balance
     */
    public void setDayBalance(BigDecimal dayBalance) { this.dayBalance = dayBalance; }
    /**
     * Gets the total credited on the given day.
     * @return the total credited on the day
     */
    public BigDecimal getDayTotalIn() { return dayTotalIn; }
    /**
     * Sets the total credited on the given day.
     * @param dayTotalIn the total credited on the day
     */
    public void setDayTotalIn(BigDecimal dayTotalIn) { this.dayTotalIn = dayTotalIn; }
    /**
     * Gets the total debited on the given day.
     * @return the total debited on the day
     */
    public BigDecimal getDayTotalOut() { return dayTotalOut; }
    /**
     * Sets the total debited on the given day.
     * @param dayTotalOut the total debited on the day
     */
    public void setDayTotalOut(BigDecimal dayTotalOut) { this.dayTotalOut = dayTotalOut; }
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
    /**
     * Gets the list of transactions for the given day.
     * @return the list of transactions for the day
     */
    public List<StatementTransaction> getDayStatement() { return dayStatement; }
    /**
     * Sets the list of transactions for the given day.
     * @param dayStatement the list of transactions for the day
     */
    public void setDayStatement(List<StatementTransaction> dayStatement) { this.dayStatement = dayStatement; }
}
