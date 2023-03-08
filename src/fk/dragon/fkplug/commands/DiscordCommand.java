package fk.dragon.fkplug.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if(sender.isOp()){    	
	    	sender.sendMessage("");
	        sender.sendMessage("§6[§3FK§6]§8 »§7 Voici le lien du serveur §9Discord§7 sur lequel aura lieu tout les prochains évenements : §8https://discord.gg/UQbuDcrM");
	    	sender.sendMessage("");
    	} else {
	    	sender.sendMessage("");
	        sender.sendMessage("§6[§3FK§6]§8 »§7 Voici le lien du serveur §9Discord§7 sur lequel aura lieu tout les prochains évenements : §8https://discord.gg/UQbuDcrM");
	    	sender.sendMessage("");
    	}
        return true;
    }
}
