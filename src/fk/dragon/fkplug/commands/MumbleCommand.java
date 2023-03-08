package fk.dragon.fkplug.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MumbleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if(sender.isOp()){    	
	    	sender.sendMessage("");
	        sender.sendMessage("§6[§3FK§6]§8 »§7 Voici le lien du serveur §cMumble§7 : §chttps://crokmooo.github.io/MumbleRequest/");
	    	sender.sendMessage("");
    	} else {
	    	sender.sendMessage("");
	        sender.sendMessage("§6[§3FK§6]§8 »§7 Voici le lien du serveur §cMumble§7 : §chttps://crokmooo.github.io/MumbleRequest/");
	    	sender.sendMessage("");
    	}
        return true;
    }
}
