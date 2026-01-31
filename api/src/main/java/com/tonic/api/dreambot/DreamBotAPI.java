package com.tonic.api.dreambot;

import com.tonic.api.dreambot.bank.Bank;
import com.tonic.api.dreambot.camera.Camera;
import com.tonic.api.dreambot.combat.Combat;
import com.tonic.api.dreambot.dialogue.Dialogue;
import com.tonic.api.dreambot.equipment.Equipment;
import com.tonic.api.dreambot.game.Game;
import com.tonic.api.dreambot.gameobject.GameObjects;
import com.tonic.api.dreambot.grandexchange.GrandExchange;
import com.tonic.api.dreambot.grounditem.GroundItems;
import com.tonic.api.dreambot.inventory.Inventory;
import com.tonic.api.dreambot.magic.Magic;
import com.tonic.api.dreambot.npc.NPCs;
import com.tonic.api.dreambot.player.Players;
import com.tonic.api.dreambot.prayer.Prayer;
import com.tonic.api.dreambot.shop.Shop;
import com.tonic.api.dreambot.skills.Skills;
import com.tonic.api.dreambot.tabs.Tabs;
import com.tonic.api.dreambot.trade.Trade;
import com.tonic.api.dreambot.walking.Walking;
import com.tonic.api.dreambot.widgets.Widgets;
import com.tonic.api.dreambot.world.Worlds;

/**
 * DreamBot-style API for VitaLite
 * 
 * This package provides a wrapper around VitaLite's API that mimics the DreamBot API structure.
 * It allows users familiar with DreamBot to easily transition to VitaLite by providing similar
 * method signatures and naming conventions.
 * 
 * <h2>Thread Safety:</h2>
 * <p>
 * All interact methods in this API are thread-safe and automatically executed on the game client thread
 * using VitaLite's {@code Static.invoke()} mechanism. This means you can safely call these methods from
 * any thread (including script threads, event handlers, or UI threads) without worrying about threading
 * issues. The API handles all thread synchronization internally.
 * </p>
 * <p>
 * Example: When you call {@code NPCs.interact("Banker", "Bank")}, the wrapper internally delegates to
 * {@code NpcAPI.interact()}, which wraps the packet writing in {@code Static.invoke()} to ensure it
 * runs on the client thread. This follows the RuneLite/OSRS bot development pattern for safe interaction.
 * </p>
 * 
 * <h2>Main API Classes:</h2>
 * <h3>Entity APIs:</h3>
 * <ul>
 *   <li>{@link NPCs} - Methods for interacting with NPCs</li>
 *   <li>{@link Players} - Methods for interacting with players</li>
 *   <li>{@link GameObjects} - Methods for interacting with game objects</li>
 *   <li>{@link GroundItems} - Methods for interacting with ground items</li>
 * </ul>
 * 
 * <h3>Inventory & Equipment APIs:</h3>
 * <ul>
 *   <li>{@link Inventory} - Methods for interacting with inventory items</li>
 *   <li>{@link Equipment} - Methods for managing equipment</li>
 *   <li>{@link Bank} - Methods for interacting with the bank</li>
 * </ul>
 * 
 * <h3>Combat & Skills APIs:</h3>
 * <ul>
 *   <li>{@link Combat} - Methods for combat information</li>
 *   <li>{@link Skills} - Methods for accessing skill information</li>
 *   <li>{@link Magic} - Methods for magic and spellcasting</li>
 *   <li>{@link Prayer} - Methods for prayer management</li>
 * </ul>
 * 
 * <h3>Game Interaction APIs:</h3>
 * <ul>
 *   <li>{@link Walking} - Methods for movement and walking</li>
 *   <li>{@link Camera} - Methods for camera control</li>
 *   <li>{@link Dialogue} - Methods for dialogue interaction</li>
 *   <li>{@link Tabs} - Methods for tab switching</li>
 * </ul>
 * 
 * <h3>Trading & Commerce APIs:</h3>
 * <ul>
 *   <li>{@link GrandExchange} - Methods for Grand Exchange operations</li>
 *   <li>{@link Trade} - Methods for trading with players</li>
 *   <li>{@link Shop} - Methods for shop interaction</li>
 * </ul>
 * 
 * <h3>Utility APIs:</h3>
 * <ul>
 *   <li>{@link Game} - Methods for game state and utilities</li>
 *   <li>{@link Worlds} - Methods for world hopping</li>
 *   <li>{@link Widgets} - Methods for widget interaction</li>
 * </ul>
 * 
 * <h2>Usage Examples:</h2>
 * 
 * <h3>NPCs & Players:</h3>
 * <pre>{@code
 * // Interact with NPCs
 * NPCs.interact("Banker", "Bank");
 * 
 * // Interact with players
 * Players.interact("PlayerName", "Trade");
 * }</pre>
 * 
 * <h3>Combat & Skills:</h3>
 * <pre>{@code
 * // Check combat status
 * if (Combat.isInCombat()) {
 *     // Handle combat
 * }
 * 
 * // Get skill levels
 * int attackLevel = Skills.getLevel(Skill.ATTACK);
 * }</pre>
 * 
 * <h3>Magic & Prayer:</h3>
 * <pre>{@code
 * // Cast spells
 * Magic.cast(Standard.FIRE_BOLT, npc);
 * 
 * // Manage prayers
 * Prayer.activate(PrayerAPI.PROTECT_FROM_MELEE);
 * }</pre>
 * 
 * <h3>Movement & Camera:</h3>
 * <pre>{@code
 * // Walk to location
 * Walking.walk(new WorldPoint(3200, 3200, 0));
 * 
 * // Control camera
 * Camera.turnToNorth();
 * }</pre>
 * 
 * <h3>Dialogue & Tabs:</h3>
 * <pre>{@code
 * // Handle dialogues
 * if (Dialogue.canContinue()) {
 *     Dialogue.continueDialogue();
 * }
 * 
 * // Switch tabs
 * Tabs.openInventory();
 * }</pre>
 * 
 * @author VitaLite Team
 * @version 2.0
 */
public class DreamBotAPI {
    
    /**
     * Private constructor to prevent instantiation
     * This is a utility class with static methods only
     */
    private DreamBotAPI() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
