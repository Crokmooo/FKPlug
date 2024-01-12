package fk.crokmoo.plug.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class denyMobSpawnerChange implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void denyMobSpawnerEgg(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getItem() != null && e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.SPAWNER && e.getItem().getType().toString().endsWith("_SPAWN_EGG")) {
            if(!e.getPlayer().isOp()) {
                e.setCancelled(true);
            }
        }
    }
}
