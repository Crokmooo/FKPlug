package fk.dragon.fkplug.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class PluginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) {
            sender.sendMessage("§8[§6X§8] " + ChatColor.RED + "Usage: /plugin <load|unload> <plugin>");
            return true;
        }

        String subCommand = args[0];

        if(subCommand.equalsIgnoreCase("load")) {
            if(args.length < 2) {
                sender.sendMessage("§8[§6X§8] " + ChatColor.RED + "Usage: /plugin load <plugin>");
                return true;
            }

            Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);

            if(plugin == null) {
                sender.sendMessage("§8[§4X§8] " + ChatColor.RED + "Plugin non trouvé: §6" + args[1]);
                return true;
            }

            Bukkit.getPluginManager().enablePlugin(plugin);
            sender.sendMessage("§8[§2+§8] " + "§8Plugin chargé§7: §2" + plugin.getName() + "§8 !");
            return true;

        } else if(subCommand.equalsIgnoreCase("unload")) {
            if(args.length < 2) {
                sender.sendMessage("§8[§6X§8] " + ChatColor.RED + "Usage: /plugin unload <plugin>");
                return true;
            }

            Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);

            if(plugin == null) {
                sender.sendMessage("§8[§4X§8] " + ChatColor.RED + "Plugin non trouvé: §6" + args[1]);
                return true;
            }

            Bukkit.getPluginManager().disablePlugin(plugin);
            
            sender.sendMessage("§8[§4-§8] " + "§8Plugin déchargé§7: §4" + plugin.getName() + "§8 !");
            return true;

        } else if(subCommand.equalsIgnoreCase("list")) {
            Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
            StringBuilder pluginList = new StringBuilder(ChatColor.GREEN + "Plugins: ");
            for(int i = 0; i < plugins.length; i++) {
                if(i > 0) pluginList.append(", ");
                pluginList.append(plugins[i].getName());
            }
            sender.sendMessage(pluginList.toString());
            return true;

        } else {
            sender.sendMessage(ChatColor.RED + "Commande inconnue: " + subCommand);
            return true;
        }
    }
}
