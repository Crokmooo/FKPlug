package fk.crokmoo.plug.events;

import fk.crokmoo.plug.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class JoinServUHC implements Listener {

    private final Main plugin;
    public JoinServUHC (Main plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void clickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();
        if (e.getClickedInventory() == null) {
            return;
        }

        if (e.getClickedInventory().getType() != InventoryType.CHEST) {
            return;
        }

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        if(p.getOpenInventory().getTitle().equals("§7Serveur §6UHC")) {
            if(clickedItem.getType() == Material.PLAYER_HEAD) {
                if (!clickedItem.getItemMeta().getDisplayName().equals(ChatColor.stripColor("Partie en attente...")) ||
                        !clickedItem.getItemMeta().getDisplayName().equals(ChatColor.stripColor("Partie en cours..."))) {
                    //Donc un server
                    //add if whitelist et if trop de joueur
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    try {
                        out.writeUTF("Connect");
                        out.writeUTF(ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName()));
                    } catch (IOException ee) {
                        ee.printStackTrace();
                    }
                    p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
                }
            }
        }
    }
}
