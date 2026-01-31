package com.tonic.api.dreambot.trade;

import com.tonic.api.widgets.TradeAPI;
import com.tonic.data.wrappers.ItemEx;

import java.util.List;

/**
 * DreamBot-style Trade API wrapper for VitaLite
 * Provides convenient methods for trading with players similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link TradeAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Check if trade window is open
 * if (Trade.isOpen()) {
 *     // Offer items
 *     Trade.offer("Coins", 1000);
 *     
 *     // Accept trade
 *     if (Trade.isOnMainScreen()) {
 *         Trade.accept();
 *     }
 * }
 * }</pre>
 */
public class Trade {

    /**
     * Checks if the trade window is open
     * @return true if open
     */
    public static boolean isOpen() {
        return TradeAPI.isOpen();
    }

    /**
     * Checks if on the main trade screen (first screen)
     * @return true if on main screen
     */
    public static boolean isOnMainScreen() {
        return TradeAPI.isOnMainScreen();
    }

    /**
     * Checks if on the confirmation screen (second screen)
     * @return true if on confirmation screen
     */
    public static boolean isOnConfirmationScreen() {
        return TradeAPI.isOnConfirmationScreen();
    }

    /**
     * Gets the items we are offering
     * @return List of items being offered
     */
    public static List<ItemEx> getOfferingItems() {
        return TradeAPI.getOfferingItems();
    }

    /**
     * Gets the items we are receiving
     * @return List of items being received
     */
    public static List<ItemEx> getReceivingItems() {
        return TradeAPI.getReceivingItems();
    }

    /**
     * Offers an item by ID
     * @param id Item ID
     * @param amount Amount to offer
     */
    public static void offer(int id, int amount) {
        TradeAPI.offer(id, amount);
    }

    /**
     * Offers an item by name
     * @param name Item name
     * @param amount Amount to offer
     */
    public static void offer(String name, int amount) {
        TradeAPI.offer(name, amount);
    }

    /**
     * Removes an item from the trade by ID
     * @param id Item ID
     * @param amount Amount to remove
     */
    public static void remove(int id, int amount) {
        TradeAPI.remove(id, amount);
    }

    /**
     * Removes an item from the trade by name
     * @param name Item name
     * @param amount Amount to remove
     */
    public static void remove(String name, int amount) {
        TradeAPI.remove(name, amount);
    }

    /**
     * Accepts the current trade screen
     */
    public static void accept() {
        TradeAPI.accept();
    }

    /**
     * Declines the trade
     */
    public static void decline() {
        TradeAPI.decline();
    }

    /**
     * Checks if we have accepted the trade
     * @return true if we have accepted
     */
    public static boolean hasAccepted() {
        return TradeAPI.isAcceptedByPlayer();
    }

    /**
     * Checks if the other player has accepted
     * @return true if other player has accepted
     */
    public static boolean hasOtherAccepted() {
        return TradeAPI.isAcceptedByOther();
    }
}
