package com.tonic.api.dreambot.gameobject;

import com.tonic.api.entities.TileObjectAPI;
import com.tonic.data.wrappers.TileObjectEx;
import com.tonic.queries.TileObjectQuery;
import net.runelite.api.coords.WorldPoint;

import java.util.List;
import java.util.function.Predicate;

/**
 * DreamBot-style GameObject API wrapper for VitaLite
 * Provides convenient methods for interacting with game objects similar to DreamBot's API
 */
public class GameObjects {

    /**
     * Gets all game objects currently loaded
     * @return List of all game objects
     */
    public static List<TileObjectEx> getAll() {
        return TileObjectAPI.search().collect();
    }

    /**
     * Gets all game objects matching the given filter
     * @param filter Filter predicate
     * @return List of filtered game objects
     */
    public static List<TileObjectEx> getAll(Predicate<TileObjectEx> filter) {
        return TileObjectAPI.search().keepIf(filter).collect();
    }

    /**
     * Gets all game objects with the given name(s)
     * @param names Object names to search for
     * @return List of game objects with matching names
     */
    public static List<TileObjectEx> getAll(String... names) {
        return TileObjectAPI.search().withNames(names).collect();
    }

    /**
     * Gets all game objects with the given ID(s)
     * @param ids Object IDs to search for
     * @return List of game objects with matching IDs
     */
    public static List<TileObjectEx> getAll(int... ids) {
        return TileObjectAPI.search().withId(ids).collect();
    }

    /**
     * Gets the closest game object to the local player
     * @return Closest game object or null if none found
     */
    public static TileObjectEx closest() {
        return TileObjectAPI.search().sortNearest().first();
    }

    /**
     * Gets the closest game object matching the given filter
     * @param filter Filter predicate
     * @return Closest matching game object or null if none found
     */
    public static TileObjectEx closest(Predicate<TileObjectEx> filter) {
        return TileObjectAPI.search().keepIf(filter).sortNearest().first();
    }

    /**
     * Gets the closest game object with the given name(s)
     * @param names Object names to search for
     * @return Closest matching game object or null if none found
     */
    public static TileObjectEx closest(String... names) {
        return TileObjectAPI.search().withNames(names).sortNearest().first();
    }

    /**
     * Gets the closest game object with the given ID(s)
     * @param ids Object IDs to search for
     * @return Closest matching game object or null if none found
     */
    public static TileObjectEx closest(int... ids) {
        return TileObjectAPI.search().withId(ids).sortNearest().first();
    }

    /**
     * Gets the closest game object whose name contains the given text
     * @param text Text to search for in object name
     * @return Closest matching game object or null if none found
     */
    public static TileObjectEx closestThatContains(String text) {
        return TileObjectAPI.search().withNamesContains(text).sortNearest().first();
    }

    /**
     * Gets the closest game object at the given location
     * @param location World location
     * @return Closest game object at location or null if none found
     */
    public static TileObjectEx closest(WorldPoint location) {
        return TileObjectAPI.search().atLocation(location).sortNearest().first();
    }

    /**
     * Gets the closest game object with the given action
     * @param action Action name to search for
     * @return Closest matching game object or null if none found
     */
    public static TileObjectEx closestWithAction(String action) {
        return TileObjectAPI.search().withAction(action).sortNearest().first();
    }

    /**
     * Gets a game object at a specific location with the given name
     * @param location World location
     * @param name Object name
     * @return Game object at location or null if none found
     */
    public static TileObjectEx getObjectAt(WorldPoint location, String name) {
        return TileObjectAPI.search().atLocation(location).withNames(name).first();
    }

    /**
     * Gets a game object at a specific location with the given ID
     * @param location World location
     * @param id Object ID
     * @return Game object at location or null if none found
     */
    public static TileObjectEx getObjectAt(WorldPoint location, int id) {
        return TileObjectAPI.search().atLocation(location).withId(id).first();
    }

    /**
     * Interacts with a game object using the given action
     * @param object Game object to interact with
     * @param action Action to perform
     * @return true if interaction was initiated
     */
    public static boolean interact(TileObjectEx object, String action) {
        if (object == null) {
            return false;
        }
        TileObjectAPI.interact(object, action);
        return true;
    }

    /**
     * Interacts with a game object using the given action index
     * @param object Game object to interact with
     * @param actionIndex Action index to perform
     * @return true if interaction was initiated
     */
    public static boolean interact(TileObjectEx object, int actionIndex) {
        if (object == null) {
            return false;
        }
        TileObjectAPI.interact(object, actionIndex);
        return true;
    }

    /**
     * Finds and interacts with the closest game object with the given name and action
     * @param name Object name
     * @param action Action to perform
     * @return true if object was found and interaction was initiated
     */
    public static boolean interact(String name, String action) {
        TileObjectEx object = closest(name);
        return interact(object, action);
    }

    /**
     * Finds and interacts with the closest game object with the given ID and action
     * @param id Object ID
     * @param action Action to perform
     * @return true if object was found and interaction was initiated
     */
    public static boolean interact(int id, String action) {
        TileObjectEx object = closest(id);
        return interact(object, action);
    }

    /**
     * Creates a new game object query for advanced filtering
     * @return New TileObjectQuery instance
     */
    public static TileObjectQuery query() {
        return TileObjectAPI.search();
    }
}
