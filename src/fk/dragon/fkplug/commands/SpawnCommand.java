package fk.dragon.fkplug.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnCommand implements CommandExecutor {

    public SpawnCommand(JavaPlugin plugin) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur !");
            return false;
        }

        Player player = (Player) sender;
        player.teleport(new Location(player.getWorld(), 0.5, 54.2, 0.5));
        player.sendMessage("§6[§3FK§6]§8 » §7Téléportation au §6spawn§7...");


        return true;
    }
}
