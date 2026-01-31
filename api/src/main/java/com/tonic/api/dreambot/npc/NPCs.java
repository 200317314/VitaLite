package com.tonic.api.dreambot.npc;

import com.tonic.api.entities.NpcAPI;
import com.tonic.data.wrappers.NpcEx;
import com.tonic.queries.NpcQuery;
import net.runelite.api.coords.WorldPoint;

import java.util.List;
import java.util.function.Predicate;

/**
 * DreamBot-style NPC API wrapper for VitaLite
 * Provides convenient methods for interacting with NPCs similar to DreamBot's API
 */
public class NPCs {

    /**
     * Gets all NPCs currently loaded
     * @return List of all NPCs
     */
    public static List<NpcEx> getAll() {
        return NpcAPI.search().collect();
    }

    /**
     * Gets all NPCs matching the given filter
     * @param filter Filter predicate
     * @return List of filtered NPCs
     */
    public static List<NpcEx> getAll(Predicate<NpcEx> filter) {
        return NpcAPI.search().keepIf(filter).collect();
    }

    /**
     * Gets all NPCs with the given name(s)
     * @param names NPC names to search for
     * @return List of NPCs with matching names
     */
    public static List<NpcEx> getAll(String... names) {
        return NpcAPI.search().withName(names).collect();
    }

    /**
     * Gets all NPCs with the given ID(s)
     * @param ids NPC IDs to search for
     * @return List of NPCs with matching IDs
     */
    public static List<NpcEx> getAll(int... ids) {
        return NpcAPI.search().withId(ids).collect();
    }

    /**
     * Gets the closest NPC to the local player
     * @return Closest NPC or null if none found
     */
    public static NpcEx closest() {
        return NpcAPI.search().sortNearest().first();
    }

    /**
     * Gets the closest NPC matching the given filter
     * @param filter Filter predicate
     * @return Closest matching NPC or null if none found
     */
    public static NpcEx closest(Predicate<NpcEx> filter) {
        return NpcAPI.search().keepIf(filter).sortNearest().first();
    }

    /**
     * Gets the closest NPC with the given name(s)
     * @param names NPC names to search for
     * @return Closest matching NPC or null if none found
     */
    public static NpcEx closest(String... names) {
        return NpcAPI.search().withName(names).sortNearest().first();
    }

    /**
     * Gets the closest NPC with the given ID(s)
     * @param ids NPC IDs to search for
     * @return Closest matching NPC or null if none found
     */
    public static NpcEx closest(int... ids) {
        return NpcAPI.search().withId(ids).sortNearest().first();
    }

    /**
     * Gets the closest NPC whose name contains the given text
     * @param text Text to search for in NPC name
     * @return Closest matching NPC or null if none found
     */
    public static NpcEx closestThatContains(String text) {
        return NpcAPI.search().withNameContains(text).sortNearest().first();
    }

    /**
     * Gets the closest NPC at the given location
     * @param location World location
     * @return Closest NPC at location or null if none found
     */
    public static NpcEx closest(WorldPoint location) {
        return NpcAPI.search().atLocation(location).sortNearest().first();
    }

    /**
     * Gets the closest NPC with the given action
     * @param action Action name to search for
     * @return Closest matching NPC or null if none found
     */
    public static NpcEx closestWithAction(String action) {
        return NpcAPI.search().withAction(action).sortNearest().first();
    }

    /**
     * Interacts with an NPC using the given action
     * @param npc NPC to interact with
     * @param action Action to perform
     * @return true if interaction was initiated
     */
    public static boolean interact(NpcEx npc, String action) {
        if (npc == null) {
            return false;
        }
        NpcAPI.interact(npc, action);
        return true;
    }

    /**
     * Interacts with an NPC using the given action index
     * @param npc NPC to interact with
     * @param actionIndex Action index to perform
     * @return true if interaction was initiated
     */
    public static boolean interact(NpcEx npc, int actionIndex) {
        if (npc == null) {
            return false;
        }
        NpcAPI.interact(npc, actionIndex);
        return true;
    }

    /**
     * Finds and interacts with the closest NPC with the given name and action
     * @param name NPC name
     * @param action Action to perform
     * @return true if NPC was found and interaction was initiated
     */
    public static boolean interact(String name, String action) {
        NpcEx npc = closest(name);
        return interact(npc, action);
    }

    /**
     * Finds and interacts with the closest NPC with the given ID and action
     * @param id NPC ID
     * @param action Action to perform
     * @return true if NPC was found and interaction was initiated
     */
    public static boolean interact(int id, String action) {
        NpcEx npc = closest(id);
        return interact(npc, action);
    }

    /**
     * Creates a new NPC query for advanced filtering
     * @return New NpcQuery instance
     */
    public static NpcQuery query() {
        return NpcAPI.search();
    }
}
