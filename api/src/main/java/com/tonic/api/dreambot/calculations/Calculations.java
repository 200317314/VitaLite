package com.tonic.api.dreambot.calculations;

import com.tonic.data.wrappers.PlayerEx;
import net.runelite.api.coords.WorldPoint;

/**
 * DreamBot-style Calculations API wrapper for VitaLite
 * Provides convenient calculation methods similar to DreamBot's API
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Get distance between two points
 * WorldPoint p1 = new WorldPoint(3200, 3200, 0);
 * WorldPoint p2 = new WorldPoint(3210, 3210, 0);
 * int distance = Calculations.distance(p1, p2);
 * 
 * // Get distance from player
 * int distanceFromPlayer = Calculations.distanceToPlayer(p2);
 * }</pre>
 */
public class Calculations {

    /**
     * Calculates the distance between two world points
     * @param p1 First world point
     * @param p2 Second world point
     * @return Distance in tiles
     */
    public static int distance(WorldPoint p1, WorldPoint p2) {
        if (p1 == null || p2 == null) {
            return Integer.MAX_VALUE;
        }
        return p1.distanceTo(p2);
    }

    /**
     * Calculates the distance from a world point to the local player
     * @param point World point
     * @return Distance in tiles
     */
    public static int distanceToPlayer(WorldPoint point) {
        if (point == null) {
            return Integer.MAX_VALUE;
        }
        PlayerEx player = PlayerEx.getLocal();
        if (player == null) {
            return Integer.MAX_VALUE;
        }
        return distance(player.getWorldLocation(), point);
    }

    /**
     * Checks if two world points are on the same plane
     * @param p1 First world point
     * @param p2 Second world point
     * @return true if on same plane
     */
    public static boolean onSamePlane(WorldPoint p1, WorldPoint p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.getPlane() == p2.getPlane();
    }

    /**
     * Gets the plane/level difference between two world points
     * @param p1 First world point
     * @param p2 Second world point
     * @return Plane difference
     */
    public static int planeDifference(WorldPoint p1, WorldPoint p2) {
        if (p1 == null || p2 == null) {
            return 0;
        }
        return Math.abs(p1.getPlane() - p2.getPlane());
    }

    /**
     * Checks if a point is within a certain distance of another point
     * @param p1 First world point
     * @param p2 Second world point
     * @param maxDistance Maximum distance
     * @return true if within distance
     */
    public static boolean isWithinDistance(WorldPoint p1, WorldPoint p2, int maxDistance) {
        return distance(p1, p2) <= maxDistance;
    }

    /**
     * Checks if a point is within a certain distance of the player
     * @param point World point
     * @param maxDistance Maximum distance
     * @return true if within distance
     */
    public static boolean isWithinDistanceOfPlayer(WorldPoint point, int maxDistance) {
        return distanceToPlayer(point) <= maxDistance;
    }
}
