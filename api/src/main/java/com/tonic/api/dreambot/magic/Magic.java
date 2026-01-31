package com.tonic.api.dreambot.magic;

import com.tonic.api.widgets.MagicAPI;
import com.tonic.data.magic.Spell;
import com.tonic.data.wrappers.*;

/**
 * DreamBot-style Magic API wrapper for VitaLite
 * Provides convenient methods for magic and spellcasting similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link MagicAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Cast a spell
 * Spell fireBolt = Standard.FIRE_BOLT;
 * Magic.cast(fireBolt);
 * 
 * // Cast spell on target
 * NpcEx npc = NPCs.closest("Guard");
 * Magic.cast(fireBolt, npc);
 * 
 * // Check autocasting
 * if (Magic.isAutoCasting()) {
 *     // Already autocasting
 * }
 * }</pre>
 */
public class Magic {

    /**
     * Casts the specified spell
     * @param spell Spell to cast
     */
    public static void cast(Spell spell) {
        MagicAPI.cast(spell);
    }

    /**
     * Casts the specified spell on an NPC
     * @param spell Spell to cast
     * @param target NPC target
     */
    public static void cast(Spell spell, NpcEx target) {
        MagicAPI.cast(spell, target);
    }

    /**
     * Casts the specified spell on a player
     * @param spell Spell to cast
     * @param target Player target
     */
    public static void cast(Spell spell, PlayerEx target) {
        MagicAPI.cast(spell, target);
    }

    /**
     * Casts the specified spell on an item
     * @param spell Spell to cast
     * @param target Item target
     */
    public static void cast(Spell spell, ItemEx target) {
        MagicAPI.cast(spell, target);
    }

    /**
     * Casts the specified spell on a game object
     * @param spell Spell to cast
     * @param target Game object target
     */
    public static void cast(Spell spell, TileObjectEx target) {
        MagicAPI.cast(spell, target);
    }

    /**
     * Casts the specified spell on a ground item
     * @param spell Spell to cast
     * @param target Ground item target
     */
    public static void cast(Spell spell, TileItemEx target) {
        MagicAPI.cast(spell, target);
    }

    /**
     * Checks if autocasting is currently enabled
     * @return true if autocasting is enabled
     */
    public static boolean isAutoCasting() {
        return MagicAPI.isAutoCasting();
    }

    /**
     * Checks if autocast is usable with current equipment
     * @return true if autocast is usable
     */
    public static boolean isAutoCastUsable() {
        return MagicAPI.isAutoCastUsable();
    }

    /**
     * Sets the best available autocast spell based on player's level
     */
    public static void setBestAutoCast() {
        MagicAPI.setBestAutoCast();
    }

    /**
     * Checks if home teleport is on cooldown
     * @return true if on cooldown
     */
    public static boolean isHomeTeleportOnCooldown() {
        return MagicAPI.isHomeTeleportOnCooldown();
    }

    /**
     * Checks if defensive casting mode is enabled
     * @return true if defensive casting is enabled
     */
    public static boolean isDefensiveCasting() {
        return MagicAPI.isDefensiveCasting();
    }
}
