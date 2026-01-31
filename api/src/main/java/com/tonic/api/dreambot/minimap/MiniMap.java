package com.tonic.api.dreambot.minimap;

import com.tonic.api.widgets.MiniMapAPI;
import net.runelite.api.coords.WorldPoint;

import java.awt.*;
import java.util.List;

/**
 * DreamBot-style MiniMap API wrapper for VitaLite
 * Provides convenient methods for minimap utilities similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link MiniMapAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Draw a path on the minimap
 * List<WorldPoint> path = Arrays.asList(
 *     new WorldPoint(3200, 3200, 0),
 *     new WorldPoint(3201, 3200, 0)
 * );
 * MiniMap.drawPath(graphics, path, Color.RED);
 * 
 * // Draw a single point
 * MiniMap.drawPoint(graphics, new WorldPoint(3200, 3200, 0), Color.BLUE);
 * }</pre>
 */
public class MiniMap {

    /**
     * Draws a path on the minimap
     * @param graphics Graphics context
     * @param path List of world points representing the path
     * @param color Color to draw the path
     */
    public static void drawPath(Graphics2D graphics, List<WorldPoint> path, Color color) {
        MiniMapAPI.drawPath(graphics, path, color);
    }

    /**
     * Draws a single point on the minimap
     * @param graphics Graphics context
     * @param point World point to draw
     * @param color Color to draw the point
     */
    public static void drawPoint(Graphics2D graphics, WorldPoint point, Color color) {
        MiniMapAPI.RenderNode(graphics, point, color);
    }
}
