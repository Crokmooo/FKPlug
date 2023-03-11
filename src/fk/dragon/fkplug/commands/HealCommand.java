package fk.dragon.fkplug.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Cette commande ne peut être exécutée que par un joueur !");
                return true;
            }
            
            Player player = (Player) sender;
            
            player.setHealth(player.getMaxHealth());
            player.setFoodLevel(20);
            player.setSaturation(20);
            player.sendMessage("§6[§3FK§6]§8 »§7 Vous avez été §cguéri §7et §6nourri§7.");
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            
            if (target == null) {
                sender.sendMessage("§6[§3FK§6]§8 »§c Le joueur spécifié n'est pas en ligne !");
                return true;
            }
            
            target.setHealth(target.getMaxHealth());
            target.setFoodLevel(20);
            target.setSaturation(20);
    		target.sendMessage("§6[§3FK§6]§8 »§7 Vous avez été §cguéri §7et §6nourri§7.");
            sender.sendMessage("§6[§3FK§6]§8 »§7 Le joueur §a" + target.getName() + " §7a été §cguéri §7et §6nourri§7!");
        } else {
            sender.sendMessage("§6[§3FK§6]§8 »§c Utilisez la commande comme ceci : /heal [pseudo]");
            return true;
        }
        
        return true;
    }
}
