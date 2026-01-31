package com.tonic.api.dreambot.skills;

import com.tonic.api.game.SkillAPI;
import net.runelite.api.Skill;

/**
 * DreamBot-style Skills API wrapper for VitaLite
 * Provides convenient methods for accessing skill information similar to DreamBot's API
 * 
 * <p><b>Thread Safety:</b> All methods are thread-safe and delegate to
 * VitaLite's native {@link SkillAPI}.</p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Get skill levels
 * int attackLevel = Skills.getLevel(Skill.ATTACK);
 * int totalLevel = Skills.getTotalLevel();
 * 
 * // Get experience
 * int attackXp = Skills.getExperience(Skill.ATTACK);
 * int xpToNextLevel = Skills.getExperienceToNextLevel(Skill.ATTACK);
 * 
 * // Check boosted levels
 * int currentHp = Skills.getBoostedLevel(Skill.HITPOINTS);
 * }</pre>
 */
public class Skills {

    /**
     * Gets the base level of a skill (without boosts)
     * @param skill The skill to check
     * @return The base level
     */
    public static int getLevel(Skill skill) {
        return SkillAPI.getLevel(skill);
    }

    /**
     * Gets the boosted (current) level of a skill
     * @param skill The skill to check
     * @return The current boosted level
     */
    public static int getBoostedLevel(Skill skill) {
        return SkillAPI.getBoostedLevel(skill);
    }

    /**
     * Gets the experience in a skill
     * @param skill The skill to check
     * @return The experience amount
     */
    public static int getExperience(Skill skill) {
        return SkillAPI.getExperience(skill);
    }

    /**
     * Gets the experience needed to reach the next level
     * @param skill The skill to check
     * @return Experience needed for next level
     */
    public static int getExperienceToNextLevel(Skill skill) {
        return SkillAPI.getExperienceToNextLevel(skill);
    }

    /**
     * Gets the total level across all skills
     * @return Total level sum
     */
    public static int getTotalLevel() {
        return SkillAPI.getTotalLevel();
    }

    /**
     * Gets the total boosted level across all skills
     * @return Total boosted level sum
     */
    public static int getBoostedTotalLevel() {
        return SkillAPI.getBoostedTotalLevel();
    }

    /**
     * Gets the total experience across all skills
     * @return Total experience sum
     */
    public static int getTotalExperience() {
        return SkillAPI.getTotalExperience();
    }

    /**
     * Gets the experience required to reach a specific level
     * @param level The level to check
     * @return Experience required
     */
    public static int getExperienceAt(int level) {
        return SkillAPI.getExperienceAt(level);
    }

    /**
     * Gets the level corresponding to a given experience amount
     * @param experience The experience amount
     * @return The level for that experience
     */
    public static int getLevelAt(int experience) {
        return SkillAPI.getLevelAt(experience);
    }
}
