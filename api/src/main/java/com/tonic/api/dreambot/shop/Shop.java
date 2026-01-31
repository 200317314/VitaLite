package com.tonic.api.dreambot.shop;

import com.tonic.api.widgets.ShopAPI;
import com.tonic.data.wrappers.ItemEx;

import java.util.List;

/**
 * DreamBot-style Shop API wrapper for VitaLite
 * Provides convenient methods for shop interaction similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link ShopAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Check if shop is open
 * if (Shop.isOpen()) {
 *     // Buy items
 *     Shop.buy("Feather", 100);
 *     
 *     // Sell items
 *     Shop.sell("Raw lobster", 10);
 * }
 * }</pre>
 */
public class Shop {

    /**
     * Checks if the shop interface is open
     * @return true if open
     */
    public static boolean isOpen() {
        return ShopAPI.isOpen();
    }

    /**
     * Closes the shop interface
     */
    public static void close() {
        ShopAPI.close();
    }

    /**
     * Buys an item by ID
     * @param itemId Item ID
     * @param amount Amount to buy
     */
    public static void buy(int itemId, int amount) {
        ShopAPI.buyX(itemId, amount);
    }

    /**
     * Buys an item by name
     * @param itemName Item name
     * @param amount Amount to buy
     */
    public static void buy(String itemName, int amount) {
        ShopAPI.buyX(itemName, amount);
    }

    /**
     * Sells an item by ID
     * @param itemId Item ID
     * @param amount Amount to sell
     */
    public static void sell(int itemId, int amount) {
        ShopAPI.sellX(itemId, amount);
    }

    /**
     * Sells an item by name
     * @param itemName Item name
     * @param amount Amount to sell
     */
    public static void sell(String itemName, int amount) {
        ShopAPI.sellX(itemName, amount);
    }

    /**
     * Gets all items in the shop
     * @return List of shop items
     */
    public static List<ItemEx> getShopItems() {
        return ShopAPI.getShopItems();
    }

    /**
     * Gets a specific shop item by ID
     * @param itemId Item ID
     * @return Shop item or null
     */
    public static ItemEx getShopItem(int itemId) {
        return ShopAPI.getShopItem(itemId);
    }

    /**
     * Gets a specific shop item by name
     * @param itemName Item name
     * @return Shop item or null
     */
    public static ItemEx getShopItem(String itemName) {
        return ShopAPI.getShopItem(itemName);
    }

    /**
     * Checks if the shop contains an item by ID
     * @param itemId Item ID
     * @return true if shop contains item
     */
    public static boolean contains(int itemId) {
        return getShopItem(itemId) != null;
    }

    /**
     * Checks if the shop contains an item by name
     * @param itemName Item name
     * @return true if shop contains item
     */
    public static boolean contains(String itemName) {
        return getShopItem(itemName) != null;
    }
}
