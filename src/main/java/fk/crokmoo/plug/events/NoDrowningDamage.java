package fk.crokmoo.plug.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoDrowningDamage implements Listener {
    
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
	        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
	            event.setCancelled(true);
	        }
    }
}
