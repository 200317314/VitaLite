package com.tonic.api.dreambot.tabs;

import com.tonic.api.widgets.TabsAPI;
import com.tonic.data.Tab;

/**
 * DreamBot-style Tabs API wrapper for VitaLite
 * Provides convenient methods for tab interaction similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link TabsAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Open a specific tab
 * Tabs.open(Tab.INVENTORY);
 * 
 * // Check if tab is open
 * if (Tabs.isOpen(Tab.PRAYER)) {
 *     // Prayer tab is open
 * }
 * 
 * // Open equipment tab
 * Tabs.openEquipment();
 * }</pre>
 */
public class Tabs {

    /**
     * Opens the specified tab
     * @param tab Tab to open
     */
    public static void open(Tab tab) {
        TabsAPI.open(tab);
    }

    /**
     * Checks if the specified tab is currently open
     * @param tab Tab to check
     * @return true if tab is open
     */
    public static boolean isOpen(Tab tab) {
        return TabsAPI.isOpen(tab);
    }

    /**
     * Opens the combat tab
     */
    public static void openCombat() {
        open(Tab.COMBAT);
    }

    /**
     * Opens the stats/skills tab
     */
    public static void openSkills() {
        open(Tab.SKILLS);
    }

    /**
     * Opens the quest tab
     */
    public static void openQuests() {
        open(Tab.QUESTS);
    }

    /**
     * Opens the inventory tab
     */
    public static void openInventory() {
        open(Tab.INVENTORY);
    }

    /**
     * Opens the equipment tab
     */
    public static void openEquipment() {
        open(Tab.EQUIPMENT);
    }

    /**
     * Opens the prayer tab
     */
    public static void openPrayer() {
        open(Tab.PRAYER);
    }

    /**
     * Opens the magic tab
     */
    public static void openMagic() {
        open(Tab.MAGIC);
    }

    /**
     * Opens the friends tab
     */
    public static void openFriends() {
        open(Tab.FRIENDS);
    }

    /**
     * Opens the account management tab
     */
    public static void openAccount() {
        open(Tab.ACCOUNT);
    }

    /**
     * Opens the logout tab
     */
    public static void openLogout() {
        open(Tab.LOGOUT);
    }

    /**
     * Opens the settings tab
     */
    public static void openSettings() {
        open(Tab.SETTINGS);
    }

    /**
     * Opens the emotes tab
     */
    public static void openEmotes() {
        open(Tab.EMOTES);
    }

    /**
     * Opens the music tab
     */
    public static void openMusic() {
        open(Tab.MUSIC);
    }
}
