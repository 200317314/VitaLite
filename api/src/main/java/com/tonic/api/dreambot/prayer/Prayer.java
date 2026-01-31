package com.tonic.api.dreambot.prayer;

import com.tonic.api.widgets.PrayerAPI;

/**
 * DreamBot-style Prayer API wrapper for VitaLite
 * Provides convenient methods for prayer management similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link PrayerAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Check if a prayer is active
 * if (Prayer.isActive(PrayerAPI.PROTECT_FROM_MELEE)) {
 *     // Protected from melee
 * }
 * 
 * // Activate a prayer
 * Prayer.activate(PrayerAPI.PIETY);
 * 
 * // Deactivate all prayers
 * Prayer.deactivateAll();
 * 
 * // Use quick prayers
 * Prayer.toggleQuickPrayer();
 * }</pre>
 */
public class Prayer {

    /**
     * Checks if any prayer is currently active
     * @return true if any prayer is active
     */
    public static boolean isAnyActive() {
        return PrayerAPI.anyActive();
    }

    /**
     * Checks if a specific prayer is active
     * @param prayer Prayer to check
     * @return true if the prayer is active
     */
    public static boolean isActive(PrayerAPI prayer) {
        return prayer.isActive();
    }

    /**
     * Activates a prayer
     * @param prayer Prayer to activate
     */
    public static void activate(PrayerAPI prayer) {
        if (!prayer.isActive()) {
            prayer.toggle();
        }
    }

    /**
     * Deactivates a prayer
     * @param prayer Prayer to deactivate
     */
    public static void deactivate(PrayerAPI prayer) {
        if (prayer.isActive()) {
            prayer.toggle();
        }
    }

    /**
     * Toggles a prayer on/off
     * @param prayer Prayer to toggle
     */
    public static void toggle(PrayerAPI prayer) {
        prayer.toggle();
    }

    /**
     * Deactivates all prayers
     */
    public static void deactivateAll() {
        for (PrayerAPI prayer : PrayerAPI.values()) {
            if (prayer.isActive()) {
                prayer.toggle();
            }
        }
    }

    /**
     * Checks if quick prayer is enabled
     * @return true if quick prayer is enabled
     */
    public static boolean isQuickPrayerEnabled() {
        return PrayerAPI.isQuickPrayerEnabled();
    }

    /**
     * Toggles quick prayer on/off
     */
    public static void toggleQuickPrayer() {
        PrayerAPI.toggleQuickPrayer();
    }

    /**
     * Turns on quick prayers
     */
    public static void turnOnQuickPrayers() {
        PrayerAPI.turnOnQuickPrayers();
    }

    /**
     * Turns off quick prayers
     */
    public static void turnOffQuickPrayers() {
        PrayerAPI.turnOffQuickPrayers();
    }

    /**
     * Sets the quick prayers to the specified prayers
     * @param prayers Prayers to set as quick prayers
     */
    public static void setQuickPrayer(PrayerAPI... prayers) {
        PrayerAPI.setQuickPrayer(prayers);
    }

    /**
     * Checks if the player has the required level for a prayer
     * @param prayer Prayer to check
     * @return true if player has required level
     */
    public static boolean hasLevelFor(PrayerAPI prayer) {
        return prayer.hasLevelFor();
    }

    /**
     * Checks if a prayer is set as a quick prayer
     * @param prayer Prayer to check
     * @return true if set as quick prayer
     */
    public static boolean isQuickPrayer(PrayerAPI prayer) {
        return prayer.isQuickPrayer();
    }

    /**
     * Gets the prayer level of the player
     * @return Prayer level
     */
    public static int getPrayerLevel() {
        return com.tonic.api.game.SkillAPI.getLevel(net.runelite.api.Skill.PRAYER);
    }

    /**
     * Gets the current prayer points
     * @return Current prayer points
     */
    public static int getPrayerPoints() {
        return com.tonic.api.game.SkillAPI.getBoostedLevel(net.runelite.api.Skill.PRAYER);
    }
}
