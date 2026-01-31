package com.tonic.api.dreambot.examples;

import com.tonic.api.dreambot.bank.Bank;
import com.tonic.api.dreambot.gameobject.GameObjects;
import com.tonic.api.dreambot.grounditem.GroundItems;
import com.tonic.api.dreambot.inventory.Inventory;
import com.tonic.api.dreambot.npc.NPCs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Demonstrates thread safety of the DreamBot API wrapper
 * 
 * All interact methods in the DreamBot API wrapper are thread-safe because they
 * delegate to VitaLite's native APIs which use Static.invoke() internally.
 * 
 * This means you can safely call these methods from:
 * - Script threads
 * - Event handlers
 * - UI threads
 * - Executor services
 * - CompletableFuture callbacks
 * - Any other thread
 * 
 * The API automatically handles thread synchronization and ensures all game
 * actions are executed on the proper client thread.
 */
public class ThreadSafetyExample {

    /**
     * Example 1: Calling from a script thread (most common case)
     * This is the typical usage - your script runs in its own thread
     */
    public static void scriptThreadExample() {
        // All these calls are thread-safe, even though we're not on the client thread
        NPCs.interact("Banker", "Bank");
        
        if (Bank.isOpen()) {
            Bank.depositAllItems();
            Bank.withdraw("Logs", 28);
        }
        
        Inventory.interact("Logs", "Drop");
        GroundItems.take("Coins");
        GameObjects.interact("Door", "Open");
    }

    /**
     * Example 2: Calling from an executor service
     * Shows that interact methods can be called from any thread pool
     */
    public static void executorServiceExample() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        // Submit tasks from different threads - all thread-safe
        executor.submit(() -> {
            NPCs.interact("Banker", "Bank");
        });
        
        executor.submit(() -> {
            if (Inventory.contains("Logs")) {
                Inventory.dropAll("Logs");
            }
        });
        
        executor.submit(() -> {
            GroundItems.take("Coins");
        });
        
        executor.shutdown();
    }

    /**
     * Example 3: Calling from event callbacks
     * Event handlers typically run on different threads
     */
    public static void eventHandlerExample() {
        // Imagine this is in an event handler like onMenuOptionClicked
        Runnable eventHandler = () -> {
            // These calls are thread-safe even from event thread
            GameObjects.interact("Door", "Open");
            NPCs.interact("Guard", "Talk-to");
        };
        
        // Execute the event handler
        eventHandler.run();
    }

    /**
     * Example 4: How Static.invoke() works internally
     * 
     * This demonstrates what happens under the hood when you call an interact method:
     * 
     * When you call: NPCs.interact("Banker", "Bank")
     * 
     * 1. NPCs.interact() delegates to NpcAPI.interact()
     * 2. NpcAPI.interact() wraps the packet in Static.invoke():
     * 
     *    Static.invoke(() -> {
     *        ClickManager.click(ClickType.ACTOR);
     *        client.getPacketWriter().npcActionPacket(option, npcIndex, false);
     *    });
     * 
     * 3. Static.invoke() checks if we're on the client thread:
     *    - If YES: executes immediately
     *    - If NO: schedules on client thread and waits for completion
     * 
     * This ensures all game actions happen on the correct thread, regardless of
     * where you call the method from.
     */
    public static void internalMechanismExample() {
        // From any thread (script thread, UI thread, event thread, etc.)
        NPCs.interact("Banker", "Bank");
        
        // The above call is equivalent to manually doing this:
        // Static.invoke(() -> {
        //     // Find NPC and send interaction packet
        // });
        
        // But you don't need to do that - the wrapper handles it automatically!
    }

    /**
     * Example 5: Common mistake to avoid (no longer possible with wrapper)
     * 
     * In older bot frameworks or when using native APIs directly, you might
     * need to wrap calls yourself:
     * 
     * // DON'T DO THIS (unnecessary with DreamBot wrapper):
     * Static.invoke(() -> {
     *     NPCs.interact("Banker", "Bank");  // Already uses invoke internally!
     * });
     * 
     * The DreamBot wrapper already handles this, so double-wrapping is redundant.
     * Just call the methods directly:
     */
    public static void correctUsageExample() {
        // CORRECT - just call directly:
        NPCs.interact("Banker", "Bank");
        GameObjects.interact("Door", "Open");
        Inventory.interact("Logs", "Drop");
        
        // The wrapper handles Static.invoke() for you!
    }

    /**
     * Example 6: Benefits of automatic thread safety
     */
    public static void benefitsExample() {
        // Benefit 1: Cleaner code - no manual invoke wrapping needed
        NPCs.interact("Banker", "Bank");
        
        // Benefit 2: No race conditions - all actions are serialized on client thread
        Inventory.interact("Knife", "Use");
        Inventory.interact("Logs", "Use");
        
        // Benefit 3: Can safely use from async operations
        // CompletableFuture.supplyAsync(() -> {
        //     return NPCs.closest("Banker");  // Thread-safe query
        // }).thenAccept(banker -> {
        //     if (banker != null) {
        //         NPCs.interact(banker, "Bank");  // Thread-safe interact
        //     }
        // });
        
        // Benefit 4: Exception handling is simpler
        try {
            NPCs.interact("Banker", "Bank");
        } catch (Exception e) {
            // You'll catch the actual exception, not threading issues
            e.printStackTrace();
        }
    }
}
