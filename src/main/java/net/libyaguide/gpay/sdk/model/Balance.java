package net.libyaguide.gpay.sdk.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents the wallet balance response.
 * Contains the current available balance and the response timestamp.
 */
public class Balance {
    /** The current available balance in LYD. */
    private BigDecimal balance;
    /** The response timestamp as a Date object. */
    private Date responseTimestamp;

    /**
     * Gets the current available balance.
     * @return the balance in LYD
     */
    public BigDecimal getBalance() { return balance; }
    /**
     * Sets the current available balance.
     * @param balance the balance in LYD
     */
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    /**
     * Gets the response timestamp.
     * @return the response timestamp as a Date
     */
    public Date getResponseTimestamp() { return responseTimestamp; }
    /**
     * Sets the response timestamp.
     * @param responseTimestamp the response timestamp as a Date
     */
    public void setResponseTimestamp(Date responseTimestamp) { this.responseTimestamp = responseTimestamp; }
}
