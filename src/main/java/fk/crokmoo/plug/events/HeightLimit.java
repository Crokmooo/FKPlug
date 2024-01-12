package fk.crokmoo.plug.events;

import fk.crokmoo.plug.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class HeightLimit implements Listener {
    private final Main plugin;

    public HeightLimit (Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        int y = location.getBlockY();
        if (y < -40 && plugin.teleportEnabled) {
            Location destination = new Location(player.getWorld(), 0.5, 54.5, 0.5);
            destination.setYaw(270);
            player.teleport(destination);
            player.setFallDistance(0);

        }
    }
}
