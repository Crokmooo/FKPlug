package fk.dragon.fkplug.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ReloadCommand implements CommandExecutor {
	  
    private Plugin plugin;
    
    public ReloadCommand(Plugin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fkrl")) {
            // Rechargement du plugin
            PluginManager pluginManager = plugin.getServer().getPluginManager();
            pluginManager.disablePlugin(plugin);
            pluginManager.enablePlugin(plugin);
            sender.sendMessage("§6[§3FK§6]§8 » §7Plugin rechargé avec §2succès !");
            return true;
        }
        return false;
    }
 
}
