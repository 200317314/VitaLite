package com.tonic.api.dreambot.camera;

import com.tonic.api.game.CameraAPI;

/**
 * DreamBot-style Camera API wrapper for VitaLite
 * Provides convenient methods for camera control similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link CameraAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Get camera angles
 * int yaw = Camera.getYaw();
 * int pitch = Camera.getPitch();
 * 
 * // Set camera angles
 * Camera.setYaw(512);
 * Camera.setPitch(256);
 * 
 * // Turn camera north
 * Camera.turnToNorth();
 * }</pre>
 */
public class Camera {

    /**
     * Gets the camera yaw (horizontal rotation)
     * @return Camera yaw value
     */
    public static int getYaw() {
        return CameraAPI.getYaw();
    }

    /**
     * Gets the camera pitch (vertical angle)
     * @return Camera pitch value
     */
    public static int getPitch() {
        return CameraAPI.getPitch();
    }

    /**
     * Sets the camera yaw (horizontal rotation)
     * @param yaw Yaw value to set
     */
    public static void setYaw(int yaw) {
        CameraAPI.setYawTarget(yaw);
    }

    /**
     * Sets the camera pitch (vertical angle)
     * @param pitch Pitch value to set
     */
    public static void setPitch(int pitch) {
        CameraAPI.setPitchTarget(pitch);
    }

    /**
     * Turns the camera to face north
     */
    public static void turnToNorth() {
        CameraAPI.faceNorth();
    }

    /**
     * Turns the camera to face south
     */
    public static void turnToSouth() {
        CameraAPI.faceSouth();
    }

    /**
     * Turns the camera to face east
     */
    public static void turnToEast() {
        CameraAPI.faceEast();
    }

    /**
     * Turns the camera to face west
     */
    public static void turnToWest() {
        CameraAPI.faceWest();
    }

    /**
     * Gets the camera X position in the scene
     * @return Camera X position
     */
    public static int getX() {
        return CameraAPI.getX();
    }

    /**
     * Gets the camera Y position in the scene
     * @return Camera Y position
     */
    public static int getY() {
        return CameraAPI.getY();
    }

    /**
     * Gets the camera Z position (height) in the scene
     * @return Camera Z position
     */
    public static int getZ() {
        return CameraAPI.getZ();
    }
}
