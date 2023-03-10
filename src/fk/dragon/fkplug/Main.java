package fk.dragon.fkplug;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fk.dragon.fkplug.commands.DiscordAllCommand;
import fk.dragon.fkplug.commands.DiscordCommand;
import fk.dragon.fkplug.commands.MumbleAllCommand;
import fk.dragon.fkplug.commands.MumbleCommand;
import fk.dragon.fkplug.commands.PluginCommand;
import fk.dragon.fkplug.commands.TradCommand;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		System.out.println("Salut la console !");
		getCommand("trad").setExecutor(new TradCommand());
		getCommand("discord").setExecutor(new DiscordCommand());
		getCommand("discordall").setExecutor(new DiscordAllCommand());
		getCommand("mumble").setExecutor(new MumbleCommand());
		getCommand("mumbleall").setExecutor(new MumbleAllCommand());
		
	    PluginCommand pluginCommand = new PluginCommand();
	    getCommand("plugin").setExecutor(pluginCommand);
	    Bukkit.getServer().getPluginManager().registerEvents(this, this);
	    
	}

	   // La méthode onPlayerJoin doit être définie comme une méthode de type void
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
	      // Modifier le message de bienvenue pour les nouveaux joueurs
		event.setJoinMessage("§8[§2+§8] §7" + event.getPlayer().getName() + " §8(§2" + Bukkit.getServer().getOnlinePlayers().size() + "§6/§4" + Bukkit.getServer().getMaxPlayers() + "§8)");
        Player player = event.getPlayer();
        Location spawnLocation = new Location(player.getWorld(), 0.5, 53.5, 0.5);
        spawnLocation.setYaw(270);
        player.teleport(spawnLocation);   
	
	
	
	}
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
	      // Modifier le message de bienvenue pour les nouveaux joueurs
		event.setQuitMessage("§8[§4-§8] §7" + event.getPlayer().getName() + " §8(§2" + (Bukkit.getServer().getOnlinePlayers().size() -1) + "§6/§4" + Bukkit.getServer().getMaxPlayers() + "§8)");
	   }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setFormat("§8[§6 §8]" + "§7 %s§8:§7 %s");
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        int y = location.getBlockY();
        if (y < -40 && teleportEnabled) {
            Location destination = new Location(player.getWorld(), 0.5, 53.5, 0.5);
            destination.setYaw(270);
            player.teleport(destination);
        }
    }

    private boolean teleportEnabled = false;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("toggleTeleport")) {
            teleportEnabled = !teleportEnabled;
            if (teleportEnabled) {
                sender.sendMessage("§6[§3FK§6]§8 » §7Height Limite §2activée");
            } else {
                sender.sendMessage("§6[§3FK§6]§8 » §7Height Limite §4désactivée");
            }
            return true;
        }
        return false;
    }


	@Override
	public void onDisable() {
		System.out.println("Au revoir la console !");
	}
}
