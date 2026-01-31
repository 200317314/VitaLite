package com.tonic.api.dreambot.world;

import com.tonic.api.game.WorldsAPI;
import com.tonic.queries.WorldQuery;
import net.runelite.http.api.worlds.World;

/**
 * DreamBot-style Worlds API wrapper for VitaLite
 * Provides convenient methods for world hopping similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link WorldsAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Get current world
 * World current = Worlds.getCurrentWorld();
 * 
 * // Hop to a random members world
 * Worlds.hopRandomMembers();
 * 
 * // Hop to next world
 * Worlds.hopNext();
 * }</pre>
 */
public class Worlds {

    /**
     * Gets the current world
     * @return Current world
     */
    public static World getCurrentWorld() {
        return WorldsAPI.getCurrentWorld();
    }

    /**
     * Hops to a random members world
     */
    public static void hopRandomMembers() {
        WorldsAPI.hopRandomMembers();
    }

    /**
     * Hops to a random free-to-play world
     */
    public static void hopRandomF2P() {
        WorldsAPI.hopRandomF2p();
    }

    /**
     * Hops to the next members world
     */
    public static void hopNextMembers() {
        WorldsAPI.hopNextMembers();
    }

    /**
     * Hops to the previous members world
     */
    public static void hopPreviousMembers() {
        WorldsAPI.hopPreviousMembers();
    }

    /**
     * Hops to the next free-to-play world
     */
    public static void hopNextF2P() {
        WorldsAPI.hopNextF2p();
    }

    /**
     * Hops to the previous free-to-play world
     */
    public static void hopPreviousF2P() {
        WorldsAPI.hopPreviousF2p();
    }

    /**
     * Hops to a specific world
     * @param world World to hop to
     */
    public static void hop(World world) {
        WorldsAPI.hop(world);
    }

    /**
     * Hops to a specific world by ID
     * @param worldId World ID to hop to
     */
    public static void hop(int worldId) {
        World world = new WorldQuery().withId(worldId).first();
        if (world != null) {
            hop(world);
        }
    }

    /**
     * Creates a default world query (filters out skill total, non-main game, and PvP worlds)
     * @param includeCurrentWorld Whether to include current world
     * @return World query
     */
    public static WorldQuery query(boolean includeCurrentWorld) {
        return WorldsAPI.createDefaultQuery(includeCurrentWorld);
    }

    /**
     * Creates a world query
     * @return World query
     */
    public static WorldQuery query() {
        return new WorldQuery();
    }

    /**
     * Gets the current world number
     * @return World number
     */
    public static int getCurrentWorldNumber() {
        World world = getCurrentWorld();
        return world != null ? world.getId() : -1;
    }
}
