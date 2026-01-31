package com.tonic.api.dreambot.inventory;

import com.tonic.api.widgets.InventoryAPI;
import com.tonic.data.wrappers.ItemEx;
import com.tonic.data.wrappers.NpcEx;
import com.tonic.data.wrappers.PlayerEx;
import com.tonic.data.wrappers.TileItemEx;
import com.tonic.data.wrappers.TileObjectEx;
import com.tonic.queries.InventoryQuery;

import java.util.List;
import java.util.function.Predicate;

/**
 * DreamBot-style Inventory API wrapper for VitaLite
 * Provides convenient methods for interacting with inventory items similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All interact methods are thread-safe. They delegate to
 * VitaLite's native {@link InventoryAPI} which uses {@code Static.invoke()} via {@link WidgetAPI}
 * to ensure actions are executed on the game client thread. You can safely call these methods
 * from any thread without additional synchronization.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Check and interact with items
 * if (Inventory.contains("Logs")) {
 *     Inventory.interact("Logs", "Drop");
 * }
 * 
 * // Use item on another item
 * ItemEx knife = Inventory.getItem("Knife");
 * ItemEx logs = Inventory.getItem("Logs");
 * Inventory.useItemOn(knife, logs);
 * }</pre>
 */
public class Inventory {

    /**
     * Gets all items currently in the inventory
     * @return List of inventory items
     */
    public static List<ItemEx> getAll() {
        return InventoryAPI.getItems();
    }

    /**
     * Gets all items matching the given filter
     * @param filter Filter predicate
     * @return List of filtered items
     */
    public static List<ItemEx> getAll(Predicate<ItemEx> filter) {
        return InventoryAPI.search().keepIf(filter).collect();
    }

    /**
     * Gets all items with the given name(s)
     * @param names Item names to search for
     * @return List of items with matching names
     */
    public static List<ItemEx> getAll(String... names) {
        return InventoryAPI.search().withName(names).collect();
    }

    /**
     * Gets all items with the given ID(s)
     * @param ids Item IDs to search for
     * @return List of items with matching IDs
     */
    public static List<ItemEx> getAll(int... ids) {
        return InventoryAPI.search().withId(ids).collect();
    }

    /**
     * Gets an item by ID
     * @param id Item ID
     * @return Item or null if not found
     */
    public static ItemEx getItem(int id) {
        return InventoryAPI.getItem(id);
    }

    /**
     * Gets an item by name
     * @param name Item name
     * @return Item or null if not found
     */
    public static ItemEx getItem(String name) {
        return InventoryAPI.getItem(name);
    }

    /**
     * Gets an item matching the given filter
     * @param filter Filter predicate
     * @return Item or null if not found
     */
    public static ItemEx getItem(Predicate<ItemEx> filter) {
        return InventoryAPI.getItem(filter);
    }

    /**
     * Checks if the inventory contains an item with the given ID
     * @param id Item ID
     * @return true if item is in inventory
     */
    public static boolean contains(int id) {
        return InventoryAPI.contains(id);
    }

    /**
     * Checks if the inventory contains all of the given item IDs
     * @param ids Item IDs
     * @return true if all items are in inventory
     */
    public static boolean contains(int... ids) {
        return InventoryAPI.contains(ids);
    }

    /**
     * Checks if the inventory contains any of the given item IDs
     * @param ids Item IDs
     * @return true if any item is in inventory
     */
    public static boolean containsAny(int... ids) {
        return InventoryAPI.containsAny(ids);
    }

    /**
     * Checks if the inventory contains an item with the given name
     * @param name Item name
     * @return true if item is in inventory
     */
    public static boolean contains(String name) {
        return InventoryAPI.contains(name);
    }

    /**
     * Checks if the inventory contains all items with the given names
     * @param names Item names
     * @return true if all items are in inventory
     */
    public static boolean contains(String... names) {
        return InventoryAPI.contains(names);
    }

    /**
     * Checks if the inventory contains any items with the given names
     * @param names Item names
     * @return true if any item is in inventory
     */
    public static boolean containsAny(String... names) {
        return InventoryAPI.containsAny(names);
    }

    /**
     * Gets the count of an item by ID
     * @param id Item ID
     * @return Count of the item
     */
    public static int count(int id) {
        return InventoryAPI.count(id);
    }

    /**
     * Gets the count of items by IDs
     * @param ids Item IDs
     * @return Total count of items
     */
    public static int count(int... ids) {
        return InventoryAPI.count(ids);
    }

    /**
     * Gets the count of an item by name
     * @param name Item name
     * @return Count of the item
     */
    public static int count(String name) {
        return InventoryAPI.count(name);
    }

    /**
     * Gets the count of items by names
     * @param names Item names
     * @return Total count of items
     */
    public static int count(String... names) {
        return InventoryAPI.count(names);
    }

    /**
     * Checks if the inventory is full
     * @return true if inventory is full
     */
    public static boolean isFull() {
        return InventoryAPI.isFull();
    }

    /**
     * Checks if the inventory is empty
     * @return true if inventory is empty
     */
    public static boolean isEmpty() {
        return InventoryAPI.isEmpty();
    }

    /**
     * Gets the number of empty slots in the inventory
     * @return Number of empty slots
     */
    public static int getEmptySlots() {
        return InventoryAPI.getEmptySlots();
    }

    /**
     * Interacts with an item using the given action
     * <p>This method is thread-safe and delegates to {@link InventoryAPI#interact(ItemEx, String)}
     * which uses {@code Static.invoke()} via {@link WidgetAPI} to execute on the client thread.</p>
     * @param item Item to interact with
     * @param action Action to perform
     * @return true if interaction was initiated
     */
    public static boolean interact(ItemEx item, String action) {
        if (item == null) {
            return false;
        }
        InventoryAPI.interact(item, action);
        return true;
    }

    /**
     * Interacts with an item by ID using the given action
     * @param id Item ID
     * @param action Action to perform
     * @return true if interaction was initiated
     */
    public static boolean interact(int id, String action) {
        ItemEx item = getItem(id);
        if (item == null) {
            return false;
        }
        InventoryAPI.interact(item, action);
        return true;
    }

    /**
     * Interacts with an item by name using the given action
     * @param name Item name
     * @param action Action to perform
     * @return true if interaction was initiated
     */
    public static boolean interact(String name, String action) {
        ItemEx item = getItem(name);
        if (item == null) {
            return false;
        }
        InventoryAPI.interact(item, action);
        return true;
    }

    /**
     * Drops an item by ID
     * @param id Item ID to drop
     * @return true if drop was initiated
     */
    public static boolean drop(int id) {
        return interact(id, "Drop");
    }

    /**
     * Drops an item by name
     * @param name Item name to drop
     * @return true if drop was initiated
     */
    public static boolean drop(String name) {
        return interact(name, "Drop");
    }

    /**
     * Drops all items with the given ID
     * @param id Item ID to drop
     * @return Number of ticks it will take
     */
    public static int dropAll(int id) {
        return InventoryAPI.dropAll(id);
    }

    /**
     * Drops all items with the given name
     * @param name Item name to drop
     * @return Number of ticks it will take
     */
    public static int dropAll(String name) {
        return InventoryAPI.dropAll(name);
    }

    /**
     * Drops all items with the given IDs
     * @param ids Item IDs to drop
     * @return Number of ticks it will take
     */
    public static int dropAll(int... ids) {
        return InventoryAPI.dropAll(ids);
    }

    /**
     * Uses an item on a game object
     * @param item Item to use
     * @param object Game object to use on
     * @return true if interaction was initiated
     */
    public static boolean useItemOn(ItemEx item, TileObjectEx object) {
        if (item == null || object == null) {
            return false;
        }
        InventoryAPI.useOn(item, object);
        return true;
    }

    /**
     * Uses an item on a ground item
     * @param item Item to use
     * @param groundItem Ground item to use on
     * @return true if interaction was initiated
     */
    public static boolean useItemOn(ItemEx item, TileItemEx groundItem) {
        if (item == null || groundItem == null) {
            return false;
        }
        InventoryAPI.useOn(item, groundItem);
        return true;
    }

    /**
     * Uses an item on an NPC
     * @param item Item to use
     * @param npc NPC to use on
     * @return true if interaction was initiated
     */
    public static boolean useItemOn(ItemEx item, NpcEx npc) {
        if (item == null || npc == null) {
            return false;
        }
        InventoryAPI.useOn(item, npc);
        return true;
    }

    /**
     * Uses an item on a player
     * @param item Item to use
     * @param player Player to use on
     * @return true if interaction was initiated
     */
    public static boolean useItemOn(ItemEx item, PlayerEx player) {
        if (item == null || player == null) {
            return false;
        }
        InventoryAPI.useOn(item, player);
        return true;
    }

    /**
     * Uses an item on another item in the inventory
     * @param item Item to use
     * @param target Target item to use on
     * @return true if interaction was initiated
     */
    public static boolean useItemOn(ItemEx item, ItemEx target) {
        if (item == null || target == null) {
            return false;
        }
        InventoryAPI.useOn(item, target);
        return true;
    }

    /**
     * Drags an item to a different slot
     * @param item Item to drag
     * @param toSlot Target slot
     * @return true if drag was initiated
     */
    public static boolean dragItem(ItemEx item, int toSlot) {
        if (item == null) {
            return false;
        }
        InventoryAPI.dragItem(item, toSlot);
        return true;
    }

    /**
     * Creates an inventory query for advanced filtering
     * @return InventoryQuery
     */
    public static InventoryQuery query() {
        return InventoryAPI.search();
    }
}
