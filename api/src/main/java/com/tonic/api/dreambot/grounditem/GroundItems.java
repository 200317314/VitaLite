package com.tonic.api.dreambot.grounditem;

import com.tonic.api.entities.TileItemAPI;
import com.tonic.data.wrappers.TileItemEx;
import com.tonic.queries.TileItemQuery;
import net.runelite.api.coords.WorldPoint;

import java.util.List;
import java.util.function.Predicate;

/**
 * DreamBot-style GroundItems API wrapper for VitaLite
 * Provides convenient methods for interacting with ground items similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All interact methods are thread-safe. They delegate to
 * VitaLite's native {@link TileItemAPI} which uses {@code Static.invoke()} to ensure
 * actions are executed on the game client thread. You can safely call these methods
 * from any thread without additional synchronization.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Simple one-liner interaction
 * GroundItems.take("Coins");
 * 
 * // Find then interact pattern
 * TileItemEx loot = GroundItems.closest("Dragon bones");
 * if (loot != null) {
 *     GroundItems.take(loot);
 * }
 * }</pre>
 */
public class GroundItems {

    /**
     * Gets all ground items currently visible
     * @return List of all ground items
     */
    public static List<TileItemEx> getAll() {
        return TileItemAPI.search().collect();
    }

    /**
     * Gets all ground items matching the given filter
     * @param filter Filter predicate
     * @return List of filtered ground items
     */
    public static List<TileItemEx> getAll(Predicate<TileItemEx> filter) {
        return TileItemAPI.search().keepIf(filter).collect();
    }

    /**
     * Gets all ground items with the given name(s)
     * @param names Item names to search for
     * @return List of ground items with matching names
     */
    public static List<TileItemEx> getAll(String... names) {
        return TileItemAPI.search().withNames(names).collect();
    }

    /**
     * Gets all ground items with the given ID(s)
     * @param ids Item IDs to search for
     * @return List of ground items with matching IDs
     */
    public static List<TileItemEx> getAll(int... ids) {
        return TileItemAPI.search().withId(ids).collect();
    }

    /**
     * Gets the closest ground item to the local player
     * @return Closest ground item or null if none found
     */
    public static TileItemEx closest() {
        return TileItemAPI.search().sortNearest().first();
    }

    /**
     * Gets the closest ground item matching the given filter
     * @param filter Filter predicate
     * @return Closest matching ground item or null if none found
     */
    public static TileItemEx closest(Predicate<TileItemEx> filter) {
        return TileItemAPI.search().keepIf(filter).sortNearest().first();
    }

    /**
     * Gets the closest ground item with the given name(s)
     * @param names Item names to search for
     * @return Closest matching ground item or null if none found
     */
    public static TileItemEx closest(String... names) {
        return TileItemAPI.search().withNames(names).sortNearest().first();
    }

    /**
     * Gets the closest ground item with the given ID(s)
     * @param ids Item IDs to search for
     * @return Closest matching ground item or null if none found
     */
    public static TileItemEx closest(int... ids) {
        return TileItemAPI.search().withId(ids).sortNearest().first();
    }

    /**
     * Gets the closest ground item whose name contains the given text
     * @param text Text to search for in item name
     * @return Closest matching ground item or null if none found
     */
    public static TileItemEx closestThatContains(String text) {
        return TileItemAPI.search().withNameContains(text).sortNearest().first();
    }

    /**
     * Gets the closest ground item at the given location
     * @param location World location
     * @return Closest ground item at location or null if none found
     */
    public static TileItemEx closest(WorldPoint location) {
        return TileItemAPI.search().atLocation(location).sortNearest().first();
    }

    /**
     * Gets a ground item at a specific location with the given name
     * @param location World location
     * @param name Item name
     * @return Ground item at location or null if none found
     */
    public static TileItemEx getItemAt(WorldPoint location, String name) {
        return TileItemAPI.search().atLocation(location).withName(name).first();
    }

    /**
     * Gets a ground item at a specific location with the given ID
     * @param location World location
     * @param id Item ID
     * @return Ground item at location or null if none found
     */
    public static TileItemEx getItemAt(WorldPoint location, int id) {
        return TileItemAPI.search().atLocation(location).withId(id).first();
    }

    /**
     * Gets all ground items at the given location
     * @param location World location
     * @return List of ground items at location
     */
    public static List<TileItemEx> getAllAt(WorldPoint location) {
        return TileItemAPI.search().atLocation(location).collect();
    }

    /**
     * Interacts with a ground item using the given action
     * <p>This method is thread-safe and delegates to {@link TileItemAPI#interact(TileItemEx, String...)}
     * which uses {@code Static.invoke()} to execute on the client thread.</p>
     * @param item Ground item to interact with
     * @param action Action to perform
     * @return true if interaction was initiated
     */
    public static boolean interact(TileItemEx item, String action) {
        if (item == null) {
            return false;
        }
        TileItemAPI.interact(item, action);
        return true;
    }

    /**
     * Interacts with a ground item using the given action index
     * @param item Ground item to interact with
     * @param actionIndex Action index to perform
     * @return true if interaction was initiated
     */
    public static boolean interact(TileItemEx item, int actionIndex) {
        if (item == null) {
            return false;
        }
        TileItemAPI.interact(item, actionIndex);
        return true;
    }

    /**
     * Takes (picks up) a ground item
     * @param item Ground item to take
     * @return true if interaction was initiated
     */
    public static boolean take(TileItemEx item) {
        return interact(item, "Take");
    }

    /**
     * Finds and takes the closest ground item with the given name
     * @param name Item name
     * @return true if item was found and take was initiated
     */
    public static boolean take(String name) {
        TileItemEx item = closest(name);
        return take(item);
    }

    /**
     * Finds and takes the closest ground item with the given ID
     * @param id Item ID
     * @return true if item was found and take was initiated
     */
    public static boolean take(int id) {
        TileItemEx item = closest(id);
        return take(item);
    }

    /**
     * Finds and interacts with the closest ground item with the given name and action
     * @param name Item name
     * @param action Action to perform
     * @return true if item was found and interaction was initiated
     */
    public static boolean interact(String name, String action) {
        TileItemEx item = closest(name);
        return interact(item, action);
    }

    /**
     * Finds and interacts with the closest ground item with the given ID and action
     * @param id Item ID
     * @param action Action to perform
     * @return true if item was found and interaction was initiated
     */
    public static boolean interact(int id, String action) {
        TileItemEx item = closest(id);
        return interact(item, action);
    }

    /**
     * Checks if there are any ground items with the given name
     * @param name Item name to check
     * @return true if item exists on ground
     */
    public static boolean contains(String name) {
        return closest(name) != null;
    }

    /**
     * Checks if there are any ground items with the given ID
     * @param id Item ID to check
     * @return true if item exists on ground
     */
    public static boolean contains(int id) {
        return closest(id) != null;
    }

    /**
     * Creates a ground item query for advanced filtering
     * @return New TileItemQuery instance
     */
    public static TileItemQuery query() {
        return TileItemAPI.search();
    }
}
