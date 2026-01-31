package com.tonic.api.dreambot.combat;

import com.tonic.api.game.CombatAPI;
import com.tonic.data.wrappers.ActorEx;

/**
 * DreamBot-style Combat API wrapper for VitaLite
 * Provides convenient methods for combat information similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link CombatAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Check combat status
 * if (Combat.isInCombat()) {
 *     ActorEx target = Combat.getTarget();
 *     // Handle combat
 * }
 * 
 * // Check if under attack
 * if (Combat.isUnderAttack()) {
 *     // React to being attacked
 * }
 * }</pre>
 */
public class Combat {

    /**
     * Checks if the local player is in combat
     * @return true if in combat
     */
    public static boolean isInCombat() {
        return CombatAPI.isInCombat();
    }

    /**
     * Checks if the local player is under attack
     * @return true if under attack
     */
    public static boolean isUnderAttack() {
        return CombatAPI.isUnderAttack();
    }

    /**
     * Gets the current combat target
     * @return Current target or null if none
     */
    public static ActorEx getTarget() {
        return CombatAPI.getTarget();
    }

    /**
     * Gets the actor that is attacking the local player
     * @return Attacker or null if not under attack
     */
    public static ActorEx getAttacker() {
        return CombatAPI.getAttacker();
    }

    /**
     * Checks if auto-retaliate is enabled
     * @return true if auto-retaliate is on
     */
    public static boolean isAutoRetaliateEnabled() {
        return CombatAPI.isAutoRetaliateEnabled();
    }

    /**
     * Gets the special attack energy percentage
     * @return Special attack energy (0-100)
     */
    public static int getSpecialAttackEnergy() {
        return CombatAPI.getSpecialAttackEnergy();
    }

    /**
     * Checks if special attack is enabled
     * @return true if special attack is enabled
     */
    public static boolean isSpecialAttackEnabled() {
        return CombatAPI.isSpecialAttackEnabled();
    }

    /**
     * Gets the wilderness level at the player's current location
     * @return Wilderness level or -1 if not in wilderness
     */
    public static int getWildernessLevel() {
        return CombatAPI.getWildernessLevel();
    }

    /**
     * Checks if the player is in the wilderness
     * @return true if in wilderness
     */
    public static boolean isInWilderness() {
        return getWildernessLevel() >= 0;
    }
}
