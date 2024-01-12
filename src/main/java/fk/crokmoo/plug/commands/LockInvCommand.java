package fk.crokmoo.plug.commands;

import fk.crokmoo.plug.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LockInvCommand implements CommandExecutor {
    private final Main plugin;

    public LockInvCommand (Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("lockinv")) {
            plugin.lockinv = !plugin.lockinv;
            if (plugin.lockinv) {
                sender.sendMessage("§6[§3FK§6]§8 » §7Verrouillage d'inventaire §2activée");
            } else {
                sender.sendMessage("§6[§3FK§6]§8 » §7Verrouillage d'inventaire §4désactivée");
            }
            return true;
        }
        return false;
    }
}
