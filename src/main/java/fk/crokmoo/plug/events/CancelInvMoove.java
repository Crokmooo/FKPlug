package fk.crokmoo.plug.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CancelInvMoove implements Listener {

    @EventHandler
    public void ClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(p.getOpenInventory().getTitle().equals("§7Serveur §6UHC")) {
            e.setCancelled(true);
        }
    }
}
