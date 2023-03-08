package fk.dragon.fkplug;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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
	   }
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
	      // Modifier le message de bienvenue pour les nouveaux joueurs
		event.setQuitMessage("§8[§4-§8] §7" + event.getPlayer().getName() + " §8(§2" + (Bukkit.getServer().getOnlinePlayers().size() -1) + "§6/§4" + Bukkit.getServer().getMaxPlayers() + "§8)");
	   }
	

	@Override
	public void onDisable() {
		System.out.println("Au revoir la console !");
	}
}
