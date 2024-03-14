package me.shradinx.util;

import org.bukkit.entity.Player;

/**
 * Record for registered cooldowns
 * @param player Player to assign cooldown to
 * @param time Duration of cooldown
 * @param reason Reason for cooldown
 */
public record Cooldown(Player player, long time, String reason) {
    public Cooldown(Player player, long time, String reason) {
        this.time = time;
        this.reason = reason;
        this.player = player;
    }
}
