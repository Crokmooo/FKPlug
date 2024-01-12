package fk.crokmoo.plug.commands;

import fk.crokmoo.plug.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleTeleport implements CommandExecutor {

    private final Main plugin;

    public ToggleTeleport (Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("toggleTeleport")) {
            plugin.teleportEnabled = !plugin.teleportEnabled;
            if (plugin.teleportEnabled) {
                sender.sendMessage("§6[§3FK§6]§8 » §7Height Limite §2activée");
            } else {
                sender.sendMessage("§6[§3FK§6]§8 » §7Height Limite §4désactivée");
            }
            return true;
        }
        return false;
    }
}
