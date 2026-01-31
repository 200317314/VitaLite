package com.tonic.api.dreambot.bank;

import com.tonic.api.widgets.BankAPI;
import com.tonic.data.wrappers.ItemEx;
import com.tonic.queries.InventoryQuery;
import net.runelite.api.gameval.InventoryID;

import java.util.List;

/**
 * DreamBot-style Bank API wrapper for VitaLite
 * Provides convenient methods for interacting with the bank similar to DreamBot's API
 */
public class Bank {

    /**
     * Checks if the bank interface is currently open
     * @return true if bank is open, false otherwise
     */
    public static boolean isOpen() {
        return BankAPI.isOpen();
    }

    /**
     * Closes the bank interface if it's open
     * @return true if bank was closed or already closed
     */
    public static boolean close() {
        if (isOpen()) {
            BankAPI.close();
            return true;
        }
        return false;
    }

    /**
     * Checks if the bank contains an item with the given ID
     * @param id Item ID to check
     * @return true if item is in bank, false otherwise
     */
    public static boolean contains(int id) {
        return BankAPI.contains(id);
    }

    /**
     * Checks if the bank contains any of the given item IDs
     * @param ids Item IDs to check
     * @return true if any item is in bank, false otherwise
     */
    public static boolean contains(int... ids) {
        return BankAPI.contains(ids);
    }

    /**
     * Checks if the bank contains an item with the given name
     * @param name Item name to check
     * @return true if item is in bank, false otherwise
     */
    public static boolean contains(String name) {
        return BankAPI.contains(name);
    }

    /**
     * Gets the count of an item in the bank by ID
     * @param id Item ID
     * @return Count of the item in bank
     */
    public static int count(int id) {
        return BankAPI.count(id);
    }

    /**
     * Gets the count of an item in the bank by name
     * @param name Item name
     * @return Count of the item in bank
     */
    public static int count(String name) {
        return BankAPI.count(name);
    }

    /**
     * Gets all items currently in the bank
     * @return List of items in bank
     */
    public static List<ItemEx> getAll() {
        return InventoryQuery.fromInventoryId(InventoryID.BANK).collect();
    }

    /**
     * Gets an item from the bank by ID
     * @param id Item ID
     * @return Item or null if not found
     */
    public static ItemEx getItem(int id) {
        return InventoryQuery.fromInventoryId(InventoryID.BANK).withId(id).first();
    }

    /**
     * Gets an item from the bank by name
     * @param name Item name
     * @return Item or null if not found
     */
    public static ItemEx getItem(String name) {
        return InventoryQuery.fromInventoryId(InventoryID.BANK).withName(name).first();
    }

    /**
     * Withdraws an item from the bank by ID
     * @param id Item ID to withdraw
     * @param amount Amount to withdraw (-1 for all)
     * @return true if withdraw was initiated
     */
    public static boolean withdraw(int id, int amount) {
        if (!isOpen()) {
            return false;
        }
        BankAPI.withdraw(id, amount, false);
        return true;
    }

    /**
     * Withdraws an item from the bank by name
     * @param name Item name to withdraw
     * @param amount Amount to withdraw (-1 for all)
     * @return true if withdraw was initiated
     */
    public static boolean withdraw(String name, int amount) {
        if (!isOpen()) {
            return false;
        }
        BankAPI.withdraw(name, amount, false);
        return true;
    }

    /**
     * Withdraws an item from the bank by ID as noted
     * @param id Item ID to withdraw
     * @param amount Amount to withdraw (-1 for all)
     * @return true if withdraw was initiated
     */
    public static boolean withdrawNoted(int id, int amount) {
        if (!isOpen()) {
            return false;
        }
        BankAPI.withdraw(id, amount, true);
        return true;
    }

    /**
     * Withdraws an item from the bank by name as noted
     * @param name Item name to withdraw
     * @param amount Amount to withdraw (-1 for all)
     * @return true if withdraw was initiated
     */
    public static boolean withdrawNoted(String name, int amount) {
        if (!isOpen()) {
            return false;
        }
        BankAPI.withdraw(name, amount, true);
        return true;
    }

    /**
     * Withdraws all of an item from the bank by ID
     * @param id Item ID to withdraw
     * @return true if withdraw was initiated
     */
    public static boolean withdrawAll(int id) {
        return withdraw(id, -1);
    }

    /**
     * Withdraws all of an item from the bank by name
     * @param name Item name to withdraw
     * @return true if withdraw was initiated
     */
    public static boolean withdrawAll(String name) {
        return withdraw(name, -1);
    }

    /**
     * Deposits an item into the bank by ID
     * @param id Item ID to deposit
     * @param amount Amount to deposit (-1 for all)
     * @return true if deposit was initiated
     */
    public static boolean deposit(int id, int amount) {
        if (!isOpen()) {
            return false;
        }
        BankAPI.deposit(id, amount);
        return true;
    }

    /**
     * Deposits an item into the bank by name
     * @param name Item name to deposit
     * @param amount Amount to deposit (-1 for all)
     * @return true if deposit was initiated
     */
    public static boolean deposit(String name, int amount) {
        if (!isOpen()) {
            return false;
        }
        BankAPI.deposit(name, amount);
        return true;
    }

    /**
     * Deposits all of an item into the bank by ID
     * @param id Item ID to deposit
     * @return true if deposit was initiated
     */
    public static boolean depositAll(int id) {
        return deposit(id, -1);
    }

    /**
     * Deposits all of an item into the bank by name
     * @param name Item name to deposit
     * @return true if deposit was initiated
     */
    public static boolean depositAll(String name) {
        return deposit(name, -1);
    }

    /**
     * Deposits all items from inventory into the bank
     * @return true if deposit was initiated
     */
    public static boolean depositAllItems() {
        if (!isOpen()) {
            return false;
        }
        BankAPI.depositAll();
        return true;
    }

    /**
     * Deposits all equipment into the bank
     * @return true if deposit was initiated
     */
    public static boolean depositAllEquipment() {
        if (!isOpen()) {
            return false;
        }
        BankAPI.depositEquipment();
        return true;
    }

    /**
     * Sets the withdraw mode to noted or unnoted
     * @param noted true for noted, false for unnoted
     */
    public static void setWithdrawMode(boolean noted) {
        BankAPI.setWithdrawMode(noted);
    }

    /**
     * Checks if the current withdraw mode is noted
     * @return true if in noted mode, false otherwise
     */
    public static boolean isWithdrawAsNote() {
        return BankAPI.isWithdrawNote();
    }

    /**
     * Creates a bank query for advanced filtering
     * @return InventoryQuery for bank
     */
    public static InventoryQuery query() {
        return BankAPI.search();
    }
}
