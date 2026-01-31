package com.tonic.api.dreambot.examples;

import com.tonic.api.dreambot.bank.Bank;
import com.tonic.api.dreambot.gameobject.GameObjects;
import com.tonic.api.dreambot.grounditem.GroundItems;
import com.tonic.api.dreambot.inventory.Inventory;
import com.tonic.api.dreambot.npc.NPCs;
import com.tonic.data.wrappers.ItemEx;
import com.tonic.data.wrappers.NpcEx;
import com.tonic.data.wrappers.TileItemEx;
import com.tonic.data.wrappers.TileObjectEx;

/**
 * Example usage of the DreamBot-style API wrapper for VitaLite
 * 
 * This class demonstrates various common use cases for the DreamBot API wrapper.
 * These examples should be familiar to anyone who has used DreamBot before.
 */
public class DreamBotAPIExamples {

    /**
     * Example: Banking - Withdraw logs and deposit coins
     */
    public static void bankingExample() {
        // Find and click on a banker
        if (!Bank.isOpen()) {
            NPCs.interact("Banker", "Bank");
            // In a real script, you'd wait for the bank to open here
        }
        
        // Once bank is open
        if (Bank.isOpen()) {
            // Deposit all items currently in inventory
            Bank.depositAllItems();
            
            // Withdraw 28 logs
            Bank.withdraw("Logs", 28);
            
            // Withdraw by item ID (more reliable)
            Bank.withdraw(1511, 28); // Regular logs
            
            // Withdraw as noted
            Bank.withdrawNoted("Oak logs", 100);
            
            // Check if bank contains an item
            if (Bank.contains("Coal")) {
                Bank.withdrawAll("Coal");
            }
            
            // Get count of an item
            int coinCount = Bank.count("Coins");
            System.out.println("Coins in bank: " + coinCount);
            
            // Close bank
            Bank.close();
        }
    }

    /**
     * Example: NPC Interaction - Talk to and attack NPCs
     */
    public static void npcExample() {
        // Find the closest banker
        NpcEx banker = NPCs.closest("Banker");
        if (banker != null) {
            NPCs.interact(banker, "Bank");
        }
        
        // Or do it in one line
        NPCs.interact("Banker", "Bank");
        
        // Find closest NPC by ID
        NpcEx guard = NPCs.closest(3010); // Guard ID
        
        // Find closest NPC with specific action
        NpcEx attackableNpc = NPCs.closestWithAction("Attack");
        if (attackableNpc != null) {
            NPCs.interact(attackableNpc, "Attack");
        }
        
        // Get all NPCs with a name
        var allChickens = NPCs.getAll("Chicken");
        System.out.println("Found " + allChickens.size() + " chickens");
        
        // Find closest NPC whose name contains text
        NpcEx cow = NPCs.closestThatContains("Cow");
    }

    /**
     * Example: GameObject Interaction - Open doors, mine rocks, etc.
     */
    public static void gameObjectExample() {
        // Find and interact with a door
        GameObjects.interact("Door", "Open");
        
        // Find closest object by ID
        TileObjectEx altar = GameObjects.closest(409); // Altar ID
        
        // Find object with specific action
        TileObjectEx mineableRock = GameObjects.closestWithAction("Mine");
        if (mineableRock != null) {
            GameObjects.interact(mineableRock, "Mine");
        }
        
        // Get all objects of a type
        var allTrees = GameObjects.getAll("Tree", "Oak", "Willow");
        
        // Find closest object whose name contains text
        TileObjectEx bank = GameObjects.closestThatContains("Bank");
    }

    /**
     * Example: Inventory Management
     */
    public static void inventoryExample() {
        // Check if inventory contains an item
        if (Inventory.contains("Logs")) {
            System.out.println("We have logs!");
        }
        
        // Count items
        int logCount = Inventory.count("Logs");
        System.out.println("Log count: " + logCount);
        
        // Get an item
        ItemEx logs = Inventory.getItem("Logs");
        
        // Interact with an item
        Inventory.interact("Tinderbox", "Use");
        
        // Drop an item
        Inventory.drop("Logs");
        
        // Use item on another item
        ItemEx knife = Inventory.getItem("Knife");
        ItemEx wood = Inventory.getItem("Logs");
        if (knife != null && wood != null) {
            Inventory.useItemOn(knife, wood);
        }
    }

    /**
     * Example: Ground Items - Pick up loot
     */
    public static void groundItemsExample() {
        // Pick up the closest coins
        GroundItems.take("Coins");
        
        // Pick up by ID
        GroundItems.take(995); // Coins ID
        
        // Find closest ground item
        TileItemEx loot = GroundItems.closest("Dragon bones");
        if (loot != null) {
            GroundItems.take(loot);
        }
    }
}
