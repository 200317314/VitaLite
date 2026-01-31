package com.tonic.api.dreambot.depositbox;

import com.tonic.api.widgets.DepositBoxAPI;

/**
 * DreamBot-style DepositBox API wrapper for VitaLite
 * Provides convenient methods for deposit box interaction similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link DepositBoxAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Check if deposit box is open
 * if (DepositBox.isOpen()) {
 *     // Deposit all items
 *     DepositBox.depositAll();
 *     
 *     // Deposit specific items
 *     DepositBox.deposit("Bones", 10);
 * }
 * }</pre>
 */
public class DepositBox {

    /**
     * Checks if the deposit box is open
     * @return true if open
     */
    public static boolean isOpen() {
        return DepositBoxAPI.isOpen();
    }

    /**
     * Closes the deposit box
     */
    public static void close() {
        DepositBoxAPI.close();
    }

    /**
     * Deposits all items from inventory
     */
    public static void depositAll() {
        DepositBoxAPI.depositAll();
    }

    /**
     * Deposits all worn items
     */
    public static void depositWornItems() {
        DepositBoxAPI.depositWornItems();
    }

    /**
     * Deposits all items from looting bag
     */
    public static void depositLootingBag() {
        DepositBoxAPI.depositLootingBag();
    }

    /**
     * Deposits an item by ID
     * @param id Item ID
     * @param amount Amount to deposit (-1 for all)
     */
    public static void deposit(int id, int amount) {
        DepositBoxAPI.deposit(id, amount);
    }

    /**
     * Deposits an item by name
     * @param name Item name
     * @param amount Amount to deposit (-1 for all)
     */
    public static void deposit(String name, int amount) {
        DepositBoxAPI.deposit(name, amount);
    }

    /**
     * Deposits all items except specified IDs
     * @param excludedIds Item IDs to keep
     */
    public static void depositAllExcept(int... excludedIds) {
        DepositBoxAPI.depositAllExcept(excludedIds);
    }

    /**
     * Deposits all items except specified names
     * @param excludedNames Item names to keep
     */
    public static void depositAllExcept(String... excludedNames) {
        DepositBoxAPI.depositAllExcept(excludedNames);
    }
}
