package net.libyaguide.gpay.sdk.model;

import java.util.Date;

/**
 * Represents the result of checking a wallet.
 * Contains wallet existence, details, and ability to receive money.
 */
public class WalletCheck {
    /** Whether the wallet exists. */
    private boolean exists;
    /** The wallet gateway ID. */
    private String walletGatewayId;
    /** The name of the wallet, or null if not found. */
    private String walletName;
    /** The user account name, or null if not found. */
    private String userAccountName;
    /** Whether the wallet can receive money. */
    private boolean canReceiveMoney;
    /** The response timestamp as a Date object. */
    private Date responseTimestamp;

    // Getters and setters
    /** Gets whether the wallet exists. */
    public boolean exists() { return exists; }
    /** Sets whether the wallet exists. */
    public void setExists(boolean exists) { this.exists = exists; }
    /** Gets the wallet gateway ID. */
    public String getWalletGatewayId() { return walletGatewayId; }
    /** Sets the wallet gateway ID. */
    public void setWalletGatewayId(String walletGatewayId) { this.walletGatewayId = walletGatewayId; }
    /** Gets the name of the wallet, or null if not found. */
    public String getWalletName() { return walletName; }
    /** Sets the name of the wallet, or null if not found. */
    public void setWalletName(String walletName) { this.walletName = walletName; }
    /** Gets the user account name, or null if not found. */
    public String getUserAccountName() { return userAccountName; }
    /** Sets the user account name, or null if not found. */
    public void setUserAccountName(String userAccountName) { this.userAccountName = userAccountName; }
    /** Gets whether the wallet can receive money. */
    public boolean canReceiveMoney() { return canReceiveMoney; }
    /** Sets whether the wallet can receive money. */
    public void setCanReceiveMoney(boolean canReceiveMoney) { this.canReceiveMoney = canReceiveMoney; }
    /** Gets the response timestamp. */
    public Date getResponseTimestamp() { return responseTimestamp; }
    /** Sets the response timestamp. */
    public void setResponseTimestamp(Date responseTimestamp) { this.responseTimestamp = responseTimestamp; }
}
