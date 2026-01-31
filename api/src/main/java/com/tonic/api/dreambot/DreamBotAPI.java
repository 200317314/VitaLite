package com.tonic.api.dreambot;

import com.tonic.api.dreambot.bank.Bank;
import com.tonic.api.dreambot.gameobject.GameObjects;
import com.tonic.api.dreambot.grounditem.GroundItems;
import com.tonic.api.dreambot.inventory.Inventory;
import com.tonic.api.dreambot.npc.NPCs;

/**
 * DreamBot-style API for VitaLite
 * 
 * This package provides a wrapper around VitaLite's API that mimics the DreamBot API structure.
 * It allows users familiar with DreamBot to easily transition to VitaLite by providing similar
 * method signatures and naming conventions.
 * 
 * <h2>Main API Classes:</h2>
 * <ul>
 *   <li>{@link NPCs} - Methods for interacting with NPCs</li>
 *   <li>{@link GameObjects} - Methods for interacting with game objects</li>
 *   <li>{@link Bank} - Methods for interacting with the bank</li>
 *   <li>{@link Inventory} - Methods for interacting with inventory items</li>
 *   <li>{@link GroundItems} - Methods for interacting with ground items</li>
 * </ul>
 * 
 * <h2>Usage Examples:</h2>
 * 
 * <h3>NPCs:</h3>
 * <pre>{@code
 * // Find and interact with an NPC
 * NpcEx banker = NPCs.closest("Banker");
 * NPCs.interact(banker, "Bank");
 * 
 * // Or do it in one line
 * NPCs.interact("Banker", "Bank");
 * 
 * // Get all NPCs with a specific name
 * List<NpcEx> guards = NPCs.getAll("Guard");
 * }</pre>
 * 
 * <h3>GameObjects:</h3>
 * <pre>{@code
 * // Find and interact with a game object
 * TileObjectEx door = GameObjects.closest("Door");
 * GameObjects.interact(door, "Open");
 * 
 * // Or do it in one line
 * GameObjects.interact("Door", "Open");
 * 
 * // Get objects with specific action
 * TileObjectEx altar = GameObjects.closestWithAction("Pray");
 * }</pre>
 * 
 * <h3>Bank:</h3>
 * <pre>{@code
 * if (Bank.isOpen()) {
 *     Bank.depositAllItems();
 *     Bank.withdraw("Logs", 28);
 *     Bank.withdraw(1511, 10); // By ID
 *     Bank.close();
 * }
 * }</pre>
 * 
 * <h3>Inventory:</h3>
 * <pre>{@code
 * // Check if inventory contains an item
 * if (Inventory.contains("Logs")) {
 *     Inventory.interact("Logs", "Drop");
 * }
 * 
 * // Drop all items of a type
 * Inventory.dropAll("Logs");
 * 
 * // Use an item on another
 * ItemEx knife = Inventory.getItem("Knife");
 * ItemEx logs = Inventory.getItem("Logs");
 * Inventory.useItemOn(knife, logs);
 * }</pre>
 * 
 * <h3>GroundItems:</h3>
 * <pre>{@code
 * // Find and pick up an item
 * TileItemEx coins = GroundItems.closest("Coins");
 * GroundItems.take(coins);
 * 
 * // Or do it in one line
 * GroundItems.take("Coins");
 * 
 * // Get all items at a location
 * List<TileItemEx> items = GroundItems.getAllAt(new WorldPoint(3200, 3200, 0));
 * }</pre>
 * 
 * @author VitaLite Team
 * @version 1.0
 */
public class DreamBotAPI {
    
    /**
     * Private constructor to prevent instantiation
     * This is a utility class with static methods only
     */
    private DreamBotAPI() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    // Static references to API classes for convenience
    
    /**
     * Gets the NPCs API
     * @return NPCs API class
     */
    public static Class<NPCs> npcs() {
        return NPCs.class;
    }
    
    /**
     * Gets the GameObjects API
     * @return GameObjects API class
     */
    public static Class<GameObjects> gameObjects() {
        return GameObjects.class;
    }
    
    /**
     * Gets the Bank API
     * @return Bank API class
     */
    public static Class<Bank> bank() {
        return Bank.class;
    }
    
    /**
     * Gets the Inventory API
     * @return Inventory API class
     */
    public static Class<Inventory> inventory() {
        return Inventory.class;
    }
    
    /**
     * Gets the GroundItems API
     * @return GroundItems API class
     */
    public static Class<GroundItems> groundItems() {
        return GroundItems.class;
    }
}
