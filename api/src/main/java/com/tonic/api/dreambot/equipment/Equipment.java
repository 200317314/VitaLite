package com.tonic.api.dreambot.equipment;

import com.tonic.api.widgets.EquipmentAPI;
import com.tonic.data.EquipmentSlot;
import com.tonic.data.wrappers.ItemEx;
import com.tonic.queries.InventoryQuery;

import java.util.List;
import java.util.function.Predicate;

/**
 * DreamBot-style Equipment API wrapper for VitaLite
 * Provides convenient methods for managing equipment similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All interact methods are thread-safe and delegate to
 * VitaLite's native {@link EquipmentAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Check if item is equipped
 * if (Equipment.isEquipped("Dragon scimitar")) {
 *     // Do something
 * }
 * 
 * // Equip an item from inventory
 * Equipment.equip("Amulet of glory");
 * 
 * // Unequip an item
 * Equipment.unequip(EquipmentSlot.WEAPON);
 * 
 * // Get equipped item
 * ItemEx weapon = Equipment.getItemInSlot(EquipmentSlot.WEAPON);
 * }</pre>
 */
public class Equipment {

    /**
     * Gets all equipped items
     * @return List of all equipped items
     */
    public static List<ItemEx> getAll() {
        return EquipmentAPI.getAll();
    }

    /**
     * Checks if an item is equipped by ID
     * @param id Item ID
     * @return true if equipped
     */
    public static boolean isEquipped(int id) {
        return EquipmentAPI.isEquipped(id);
    }

    /**
     * Checks if an item is equipped by name
     * @param name Item name
     * @return true if equipped
     */
    public static boolean isEquipped(String name) {
        return EquipmentAPI.isEquipped(name);
    }

    /**
     * Checks if an item matching the filter is equipped
     * @param filter Filter predicate
     * @return true if a matching item is equipped
     */
    public static boolean isEquipped(Predicate<ItemEx> filter) {
        return EquipmentAPI.isEquipped(filter);
    }

    /**
     * Gets an equipped item by ID
     * @param id Item ID
     * @return Equipped item or null
     */
    public static ItemEx getItem(int id) {
        return EquipmentAPI.getItem(id);
    }

    /**
     * Gets an equipped item by name
     * @param name Item name
     * @return Equipped item or null
     */
    public static ItemEx getItem(String name) {
        return EquipmentAPI.getItem(name);
    }

    /**
     * Gets an equipped item matching the filter
     * @param filter Filter predicate
     * @return Matching equipped item or null
     */
    public static ItemEx getItem(Predicate<ItemEx> filter) {
        return EquipmentAPI.getItem(filter);
    }

    /**
     * Gets the item in a specific equipment slot
     * @param slot Equipment slot
     * @return Item in slot or null
     */
    public static ItemEx getItemInSlot(EquipmentSlot slot) {
        return EquipmentAPI.fromSlot(slot);
    }

    /**
     * Equips an item from inventory by ID
     * @param id Item ID to equip
     */
    public static void equip(int id) {
        EquipmentAPI.equip(id);
    }

    /**
     * Equips an item from inventory by name
     * @param name Item name to equip
     */
    public static void equip(String name) {
        EquipmentAPI.equip(name);
    }

    /**
     * Unequips an item in the specified slot
     * @param slot Equipment slot to unequip
     */
    public static void unequip(EquipmentSlot slot) {
        EquipmentAPI.unEquip(slot);
    }

    /**
     * Unequips a specific item
     * @param item Item to unequip
     */
    public static void unequip(ItemEx item) {
        EquipmentAPI.unEquip(item);
    }

    /**
     * Unequips all items
     */
    public static void unequipAll() {
        EquipmentAPI.unequipAll();
    }

    /**
     * Gets the count of a specific equipped item
     * @param id Item ID
     * @return Count of equipped items with that ID
     */
    public static int getCount(int id) {
        return EquipmentAPI.getCount(id);
    }

    /**
     * Interacts with an equipped item
     * @param item Item to interact with
     * @param action Action name
     */
    public static void interact(ItemEx item, String action) {
        EquipmentAPI.interact(item, action);
    }

    /**
     * Interacts with an equipped item by action index
     * @param item Item to interact with
     * @param actionIndex Action index
     */
    public static void interact(ItemEx item, int actionIndex) {
        EquipmentAPI.interact(item, actionIndex);
    }

    /**
     * Gets a query builder for advanced filtering
     * @return Inventory query builder for equipment
     */
    public static InventoryQuery query() {
        return EquipmentAPI.search();
    }

    /**
     * Checks if equipment tab is full (all slots used)
     * @return true if all equipment slots are occupied
     */
    public static boolean isFull() {
        return getAll().size() >= 11; // 11 equipment slots
    }

    /**
     * Checks if any equipment slot is empty
     * @return true if at least one slot is empty
     */
    public static boolean hasEmptySlot() {
        return !isFull();
    }
}
