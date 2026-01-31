package com.tonic.api.dreambot.grandexchange;

import com.tonic.api.widgets.GrandExchangeAPI;
import net.runelite.api.GrandExchangeOffer;

import java.util.List;

/**
 * DreamBot-style Grand Exchange API wrapper for VitaLite
 * Provides convenient methods for Grand Exchange operations similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link GrandExchangeAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Check if GE is open
 * if (GrandExchange.isOpen()) {
 *     // Collect items
 *     if (GrandExchange.canCollect()) {
 *         GrandExchange.collect();
 *     }
 *     
 *     // Get active offers
 *     List<GrandExchangeOffer> offers = GrandExchange.getOffers();
 * }
 * }</pre>
 */
public class GrandExchange {

    /**
     * Checks if the Grand Exchange interface is open
     * @return true if open
     */
    public static boolean isOpen() {
        return GrandExchangeAPI.isOpen();
    }

    /**
     * Closes the Grand Exchange interface
     */
    public static void close() {
        GrandExchangeAPI.close();
    }

    /**
     * Checks if items can be collected
     * @return true if collect button is available
     */
    public static boolean canCollect() {
        return GrandExchangeAPI.canCollect();
    }

    /**
     * Collects all completed offers
     */
    public static void collect() {
        GrandExchangeAPI.collect();
    }

    /**
     * Gets all active Grand Exchange offers
     * @return List of offers
     */
    public static List<GrandExchangeOffer> getOffers() {
        return GrandExchangeAPI.getOffers();
    }

    /**
     * Aborts an offer for the specified item
     * @param itemId Item ID of offer to abort
     */
    public static void abortOffer(int itemId) {
        GrandExchangeAPI.abortOffer(itemId);
    }

    /**
     * Checks if an offer is complete for the specified item
     * @param itemId Item ID to check
     * @return true if offer is complete
     */
    public static boolean isOfferComplete(int itemId) {
        return GrandExchangeAPI.isOfferComplete(itemId);
    }

    /**
     * Gets the first empty slot index
     * @return Empty slot index or -1 if no empty slots
     */
    public static int getFirstEmptySlot() {
        return GrandExchangeAPI.getFirstEmptySlot();
    }
}
