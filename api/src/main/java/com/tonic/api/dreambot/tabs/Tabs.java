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
 * Tabs.open(Tab.INVENTORY_TAB);
 * 
 * // Check if tab is open
 * if (Tabs.isOpen(Tab.PRAYER_TAB)) {
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
        open(Tab.COMBAT_TAB);
    }

    /**
     * Opens the stats/skills tab
     */
    public static void openSkills() {
        open(Tab.EXP_TAB);
    }

    /**
     * Opens the quest tab
     */
    public static void openQuests() {
        open(Tab.QUESTS_TAB);
    }

    /**
     * Opens the inventory tab
     */
    public static void openInventory() {
        open(Tab.INVENTORY_TAB);
    }

    /**
     * Opens the equipment tab
     */
    public static void openEquipment() {
        open(Tab.EQUIPMENT_TAB);
    }

    /**
     * Opens the prayer tab
     */
    public static void openPrayer() {
        open(Tab.PRAYER_TAB);
    }

    /**
     * Opens the magic tab
     */
    public static void openMagic() {
        open(Tab.SPELLBOOK_TAB);
    }

    /**
     * Opens the friends tab
     */
    public static void openFriends() {
        open(Tab.FRIENDS_TAB);
    }

    /**
     * Opens the account management tab
     */
    public static void openAccount() {
        open(Tab.ACCOUNT_TAB);
    }

    /**
     * Opens the logout tab
     */
    public static void openLogout() {
        open(Tab.LOGOUT_TAB);
    }

    /**
     * Opens the settings tab
     */
    public static void openSettings() {
        open(Tab.SETTINGS_TAB);
    }

    /**
     * Opens the emotes tab
     */
    public static void openEmotes() {
        open(Tab.EMOTES_TAB);
    }

    /**
     * Opens the music tab
     */
    public static void openMusic() {
        open(Tab.MUSIC_TAB);
    }
}
