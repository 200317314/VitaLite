package com.tonic.api.dreambot.game;

import com.tonic.api.game.GameAPI;

/**
 * DreamBot-style Game API wrapper for VitaLite
 * Provides convenient methods for game state and utilities similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link GameAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Check game state
 * if (Game.isLoggedIn()) {
 *     // Perform actions
 * }
 * 
 * // Get wilderness level
 * int wildyLevel = Game.getWildernessLevel();
 * 
 * // Logout
 * Game.logout();
 * }</pre>
 */
public class Game {

    /**
     * Checks if the player is logged in
     * @return true if logged in
     */
    public static boolean isLoggedIn() {
        return GameAPI.isLoggedIn();
    }

    /**
     * Checks if on the login screen
     * @return true if on login screen
     */
    public static boolean isOnLoginScreen() {
        return GameAPI.isOnLoginScreen();
    }

    /**
     * Gets the current wilderness level
     * @return Wilderness level or 0 if not in wilderness
     */
    public static int getWildernessLevel() {
        return GameAPI.getWildyLevel();
    }

    /**
     * Checks if in the wilderness
     * @return true if in wilderness
     */
    public static boolean isInWilderness() {
        return getWildernessLevel() > 0;
    }

    /**
     * Logs out of the game
     */
    public static void logout() {
        GameAPI.logout();
    }

    /**
     * Gets the current world number
     * @return Current world number
     */
    public static int getCurrentWorld() {
        return com.tonic.Static.getClient().getWorld();
    }

    /**
     * Gets the current game tick count
     * @return Game tick count
     */
    public static int getGameTick() {
        return com.tonic.Static.getClient().getTickCount();
    }

    /**
     * Gets the player's username
     * @return Player username or null if not logged in
     */
    public static String getUsername() {
        return com.tonic.Static.invoke(() -> {
            var player = com.tonic.Static.getClient().getLocalPlayer();
            return player != null ? player.getName() : null;
        });
    }

    /**
     * Gets the current FPS
     * @return Current frames per second
     */
    public static int getFPS() {
        return com.tonic.Static.getClient().getFPS();
    }
}
