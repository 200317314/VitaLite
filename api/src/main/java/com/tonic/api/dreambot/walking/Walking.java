package com.tonic.api.dreambot.walking;

import com.tonic.api.game.MovementAPI;
import net.runelite.api.coords.WorldPoint;

/**
 * DreamBot-style Walking API wrapper for VitaLite
 * Provides convenient methods for movement and walking similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link MovementAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Walk to a location
 * WorldPoint destination = new WorldPoint(3200, 3200, 0);
 * Walking.walk(destination);
 * 
 * // Check if moving
 * if (Walking.isMoving()) {
 *     // Wait for movement to complete
 * }
 * 
 * // Manage run energy
 * if (Walking.getRunEnergy() > 20 && !Walking.isRunEnabled()) {
 *     Walking.toggleRun();
 * }
 * }</pre>
 */
public class Walking {

    /**
     * Walks to the specified world point
     * @param destination World point to walk to
     */
    public static void walk(WorldPoint destination) {
        MovementAPI.walkToWorldPoint(destination);
    }

    /**
     * Walks to the specified coordinates
     * @param x X coordinate
     * @param y Y coordinate
     * @param plane Plane/floor level
     */
    public static void walk(int x, int y, int plane) {
        walk(new WorldPoint(x, y, plane));
    }

    /**
     * Checks if the player is currently moving
     * @return true if moving
     */
    public static boolean isMoving() {
        return MovementAPI.isMoving();
    }

    /**
     * Gets the player's destination world point
     * @return Destination or null if not moving
     */
    public static WorldPoint getDestination() {
        return MovementAPI.getDestinationWorldPoint();
    }

    /**
     * Gets the current run energy
     * @return Run energy (0-100)
     */
    public static int getRunEnergy() {
        return MovementAPI.getRunEnergy();
    }

    /**
     * Checks if run is enabled
     * @return true if run is enabled
     */
    public static boolean isRunEnabled() {
        return MovementAPI.isRunEnabled();
    }

    /**
     * Toggles run mode on/off
     */
    public static void toggleRun() {
        MovementAPI.toggleRun();
    }

    /**
     * Checks if stamina potion effect is active
     * @return true if stamina is active
     */
    public static boolean isStaminaActive() {
        return MovementAPI.staminaInEffect();
    }

    /**
     * Enables run if it's not already enabled
     */
    public static void enableRun() {
        if (!isRunEnabled()) {
            toggleRun();
        }
    }

    /**
     * Disables run if it's currently enabled
     */
    public static void disableRun() {
        if (isRunEnabled()) {
            toggleRun();
        }
    }
}
