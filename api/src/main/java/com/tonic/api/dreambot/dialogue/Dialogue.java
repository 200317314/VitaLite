package com.tonic.api.dreambot.dialogue;

import com.tonic.api.widgets.DialogueAPI;

import java.util.List;

/**
 * DreamBot-style Dialogue API wrapper for VitaLite
 * Provides convenient methods for dialogue interaction similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link DialogueAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Check if dialogue is open
 * if (Dialogue.isOpen()) {
 *     // Continue through dialogue
 *     if (Dialogue.canContinue()) {
 *         Dialogue.continueDialogue();
 *     }
 *     
 *     // Select an option
 *     if (Dialogue.hasOptions()) {
 *         Dialogue.selectOption("Yes");
 *     }
 * }
 * }</pre>
 */
public class Dialogue {

    /**
     * Checks if a dialogue is currently open
     * @return true if dialogue is open
     */
    public static boolean isOpen() {
        return DialogueAPI.inDialogue();
    }

    /**
     * Gets the current dialogue header (speaker name or context)
     * @return Dialogue header text
     */
    public static String getHeader() {
        return DialogueAPI.getDialogueHeader();
    }

    /**
     * Gets the current dialogue text
     * @return Dialogue text
     */
    public static String getText() {
        return DialogueAPI.getDialogueText();
    }

    /**
     * Checks if the dialogue can be continued
     * @return true if continue button is available
     */
    public static boolean canContinue() {
        return DialogueAPI.canContinue();
    }

    /**
     * Continues the current dialogue
     * @return true if dialogue was continued
     */
    public static boolean continueDialogue() {
        return DialogueAPI.continueDialogue();
    }

    /**
     * Checks if dialogue options are available
     * @return true if options are available
     */
    public static boolean hasOptions() {
        return DialogueAPI.hasOptions();
    }

    /**
     * Gets the available dialogue options
     * @return List of dialogue option texts
     */
    public static List<String> getOptions() {
        return DialogueAPI.getOptions();
    }

    /**
     * Selects a dialogue option by text (substring match)
     * @param text Option text to select
     * @return true if option was selected
     */
    public static boolean selectOption(String text) {
        return DialogueAPI.selectOption(text);
    }

    /**
     * Selects a dialogue option by index
     * @param index Option index (0-based)
     * @return true if option was selected
     */
    public static boolean selectOptionIndex(int index) {
        List<String> options = getOptions();
        if (index >= 0 && index < options.size()) {
            return DialogueAPI.selectOption(options.get(index));
        }
        return false;
    }

    /**
     * Completes the current dialogue (continues until no more dialogue)
     * @return true if dialogue was completed
     */
    public static boolean completeDialogue() {
        return DialogueAPI.completeDialogue();
    }

    /**
     * Checks if currently viewing an NPC dialogue
     * @return true if viewing NPC dialogue
     */
    public static boolean isNPCDialogue() {
        String header = getHeader();
        return !header.equals("Player") && !header.equals("Select an Option") && !header.equals("UNKNOWN");
    }

    /**
     * Checks if currently viewing a player dialogue
     * @return true if viewing player dialogue
     */
    public static boolean isPlayerDialogue() {
        return "Player".equals(getHeader());
    }
}
