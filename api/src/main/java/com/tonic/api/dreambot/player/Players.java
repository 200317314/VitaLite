package com.tonic.api.dreambot.player;

import com.tonic.api.entities.PlayerAPI;
import com.tonic.data.wrappers.PlayerEx;
import com.tonic.queries.PlayerQuery;
import net.runelite.api.coords.WorldPoint;

import java.util.List;
import java.util.function.Predicate;

/**
 * DreamBot-style Player API wrapper for VitaLite
 * Provides convenient methods for interacting with players similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All interact methods are thread-safe. They delegate to
 * VitaLite's native {@link PlayerAPI} which uses {@code Static.invoke()} to ensure
 * actions are executed on the game client thread. You can safely call these methods
 * from any thread without additional synchronization.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Simple interaction
 * Players.interact("PlayerName", "Trade");
 * 
 * // Find then interact pattern
 * PlayerEx player = Players.closest("PlayerName");
 * if (player != null) {
 *     Players.interact(player, "Follow");
 * }
 * 
 * // Get local player
 * PlayerEx me = Players.getLocal();
 * }</pre>
 */
public class Players {

    /**
     * Gets all players currently loaded
     * @return List of all players
     */
    public static List<PlayerEx> getAll() {
        return PlayerAPI.search().collect();
    }

    /**
     * Gets all players matching the given filter
     * @param filter Filter predicate
     * @return List of filtered players
     */
    public static List<PlayerEx> getAll(Predicate<PlayerEx> filter) {
        return PlayerAPI.search().keepIf(filter).collect();
    }

    /**
     * Gets all players with the given name(s)
     * @param names Player names to search for
     * @return List of players with matching names
     */
    public static List<PlayerEx> getAll(String... names) {
        return PlayerAPI.search().withNames(names).collect();
    }

    /**
     * Gets the closest player to the local player
     * @return Closest player or null if none found
     */
    public static PlayerEx closest() {
        return PlayerAPI.search().sortNearest().first();
    }

    /**
     * Gets the closest player matching the given filter
     * @param filter Filter predicate
     * @return Closest matching player or null if none found
     */
    public static PlayerEx closest(Predicate<PlayerEx> filter) {
        return PlayerAPI.search().keepIf(filter).sortNearest().first();
    }

    /**
     * Gets the closest player with the given name(s)
     * @param names Player names to search for
     * @return Closest matching player or null if none found
     */
    public static PlayerEx closest(String... names) {
        return PlayerAPI.search().withNames(names).sortNearest().first();
    }

    /**
     * Gets the closest player within the given distance
     * @param distance Maximum distance in tiles
     * @return Closest player within distance or null if none found
     */
    public static PlayerEx closestWithinDistance(int distance) {
        return PlayerAPI.search().withinDistance(distance).sortNearest().first();
    }

    /**
     * Gets the local player
     * @return Local player
     */
    public static PlayerEx getLocal() {
        return PlayerEx.getLocal();
    }

    /**
     * Interacts with a player using the first matching action
     * @param player Player to interact with
     * @param actions Action names to try (uses first matching)
     * @return true if interaction was attempted
     */
    public static boolean interact(PlayerEx player, String... actions) {
        if (player == null) {
            return false;
        }
        PlayerAPI.interact(player, actions);
        return true;
    }

    /**
     * Interacts with the closest player with the given name using the specified action
     * @param name Player name
     * @param action Action name
     * @return true if player was found and interaction was attempted
     */
    public static boolean interact(String name, String action) {
        PlayerEx player = closest(name);
        if (player == null) {
            return false;
        }
        return interact(player, action);
    }

    /**
     * Interacts with a player using the action at the specified index
     * @param player Player to interact with
     * @param actionIndex Index of the action
     * @return true if interaction was attempted
     */
    public static boolean interact(PlayerEx player, int actionIndex) {
        if (player == null) {
            return false;
        }
        PlayerAPI.interact(player, actionIndex);
        return true;
    }

    /**
     * Gets a query builder for advanced filtering
     * @return Player query builder
     */
    public static PlayerQuery query() {
        return PlayerAPI.search();
    }

    /**
     * Checks if local player is idle
     * @return true if idle
     */
    public static boolean isIdle() {
        return getLocal().isIdle();
    }

    /**
     * Checks if local player is animating
     * @return true if animating
     */
    public static boolean isAnimating() {
        return getLocal().isAnimating();
    }

    /**
     * Checks if local player is moving
     * @return true if moving
     */
    public static boolean isMoving() {
        return getLocal().isMoving();
    }

    /**
     * Gets the local player's current world location
     * @return Current world point
     */
    public static WorldPoint getLocation() {
        return getLocal().getWorldLocation();
    }
}
