package fk.dragon.fkplug.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur !");
            return false;
        }

        Player player = (Player) sender;
        if (player.getServer().getName().equalsIgnoreCase("Spawn")) {
            player.teleport(new Location(player.getWorld(), 0.5, 54.2, 0.5));
            player.sendMessage("§6[§3FK§6]§8 » §7Téléportation au §6spawn§7...");
            
            
        }         
        
        if (player.getServer().getName().equalsIgnoreCase("XTREM")) {
            player.sendMessage("§6[§3FK§6]§8 » §7Téléportation au §6spawn§7...");
            // Redirection du joueur vers le serveur "Lobby" via l'API BungeeCord
            World world = Bukkit.getWorld("XTREM");
            Location spawnLocation = world.getSpawnLocation();
            player.teleport(spawnLocation);

            
        } else {
        	player.sendMessage("§6[§3FK§6]§8 » §cLe /spawn ne marche pas sur ce serveur. Si il s'agit d'une erreur, parlez-en aux administrateurs.");
        }

        return true;
    }
}
