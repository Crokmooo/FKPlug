package fk.dragon.fkplug.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MumbleAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if(sender.isOp()){    	
	    	Bukkit.broadcastMessage("");
	        Bukkit.broadcastMessage("§6[§3FK§6]§8 »§7 Voici le lien du serveur §cMumble§7 : §chttps://crokmooo.github.io/MumbleRequest/");
	    	Bukkit.broadcastMessage("");
    	} else {
    	    Bukkit.broadcastMessage("§cVous n'avez pas les autorisations nécessaires pour voir ce lien.");

    	}
        return true;
    }
}
