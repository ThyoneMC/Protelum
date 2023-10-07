package org.thyone.teamme.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.thyone.teamme.database.TeamStorage;

public class PlayerTakeDamage implements Listener {
    @EventHandler
    public void onPlayerGetDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(event.getDamager() instanceof Player playerDamager)) return;

        if (TeamStorage.isEqualTeam(player.getUniqueId(), playerDamager.getUniqueId())) event.setCancelled(true);
    }
}
