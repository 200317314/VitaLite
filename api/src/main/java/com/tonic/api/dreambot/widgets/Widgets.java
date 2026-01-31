package com.tonic.api.dreambot.widgets;

import com.tonic.api.widgets.WidgetAPI;
import com.tonic.queries.WidgetQuery;
import net.runelite.api.widgets.Widget;

/**
 * DreamBot-style Widgets API wrapper for VitaLite
 * Provides convenient methods for widget interaction similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link WidgetAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Get a widget
 * Widget widget = Widgets.get(123, 4);
 * 
 * // Check if visible
 * if (Widgets.isVisible(widget)) {
 *     // Interact with widget
 *     Widgets.interact(widget, "Continue");
 * }
 * }</pre>
 */
public class Widgets {

    /**
     * Gets a widget by group and child ID
     * @param groupId Widget group ID
     * @param childId Widget child ID
     * @return Widget or null
     */
    public static Widget get(int groupId, int childId) {
        return WidgetAPI.get(groupId, childId);
    }

    /**
     * Gets a widget by packed ID
     * @param packedId Packed widget ID
     * @return Widget or null
     */
    public static Widget get(int packedId) {
        return WidgetAPI.get(packedId);
    }

    /**
     * Checks if a widget is visible
     * @param widget Widget to check
     * @return true if visible
     */
    public static boolean isVisible(Widget widget) {
        return WidgetAPI.isVisible(widget);
    }

    /**
     * Checks if a widget is visible by IDs
     * @param groupId Widget group ID
     * @param childId Widget child ID
     * @return true if visible
     */
    public static boolean isVisible(int groupId, int childId) {
        Widget widget = get(groupId, childId);
        return isVisible(widget);
    }

    /**
     * Checks if a widget is visible by packed ID
     * @param packedId Packed widget ID
     * @return true if visible
     */
    public static boolean isVisible(int packedId) {
        Widget widget = get(packedId);
        return isVisible(widget);
    }

    /**
     * Interacts with a widget by action name
     * @param widget Widget to interact with
     * @param actions Action names to try
     */
    public static void interact(Widget widget, String... actions) {
        WidgetAPI.interact(widget, actions);
    }

    /**
     * Interacts with a widget by action index
     * @param widget Widget to interact with
     * @param actionIndex Action index
     */
    public static void interact(Widget widget, int actionIndex) {
        WidgetAPI.interact(widget, actionIndex);
    }

    /**
     * Gets the text of a widget
     * @param widget Widget to get text from
     * @return Widget text or null
     */
    public static String getText(Widget widget) {
        return widget != null ? widget.getText() : null;
    }

    /**
     * Gets the text of a widget by IDs
     * @param groupId Widget group ID
     * @param childId Widget child ID
     * @return Widget text or null
     */
    public static String getText(int groupId, int childId) {
        Widget widget = get(groupId, childId);
        return getText(widget);
    }

    /**
     * Creates a widget query for advanced filtering
     * @return Widget query
     */
    public static WidgetQuery query() {
        return WidgetAPI.search();
    }

    /**
     * Checks if any widget with the specified text is visible
     * @param text Text to search for
     * @return true if widget with text is visible
     */
    public static boolean isTextVisible(String text) {
        return WidgetAPI.search()
                .withText(text)
                .isVisible()
                .first() != null;
    }
}
