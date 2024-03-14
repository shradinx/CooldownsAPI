package me.shradinx;

import me.shradinx.util.Cooldown;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * <strong>Author: Shradinx</strong>
 */
public class CooldownsAPI {
    
    private final HashMap<UUID, Cooldown> cooldowns;
    
    public CooldownsAPI(HashMap<UUID, Cooldown> cooldowns) {
        this.cooldowns = cooldowns;
    }
    
    /**
     * @param player Player to assign cooldown to
     * @param seconds Duration of cooldown
     * @param reason Reason for cooldown
     * @return True if successfully added, false if already on cooldown
     */
    public boolean addCooldown(Player player, double seconds, String reason) {
        UUID uuid = player.getUniqueId();
        long timeLeft = getTimeLeft(player, seconds);
        if (isOnCooldown(player)) return false;
        cooldowns.put(uuid, new Cooldown(player, System.currentTimeMillis(), reason));
        return true;
    }
    
    /**
     * @param player Player to remove cooldown from
     */
    public void removeCooldown(Player player) {
        cooldowns.remove(player.getUniqueId());
    }
    
    /**
     * @param player Player to check for cooldown
     * @return True if player is on cooldown, false otherwise
     */
    public boolean isOnCooldown(Player player) {
        return cooldowns.containsKey(player.getUniqueId());
    }
    
    /**
     * @param player Player to get cooldown for
     * @param seconds Initial duration of cooldown
     * @return The current time left on the cooldown
     */
    public long getTimeLeft(Player player, double seconds) {
        return (long) (((cooldowns.get(player.getUniqueId()).time() / 1000) + seconds) - (System.currentTimeMillis() / 1000));
    }
    
}
