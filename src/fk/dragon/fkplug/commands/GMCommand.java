package fk.dragon.fkplug.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;


public class GMCommand implements CommandExecutor, TabCompleter {
	@Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> options = new ArrayList<>();
        
        if (args.length == 1) {
            options.add("0");
            options.add("1");
            options.add("2");
            options.add("3");           
        }
        return options;
    }

	
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage("§6[§3FK§6]§8 » §cLa commande s'utilise comme ça : /gm [§20 §c| §61 §c| §a2 §c| §53§c]");
            return false;
        }

        String gm = args[0];
        
        player.sendMessage("");
        
        if (gm.equalsIgnoreCase("0")) {
        	player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage("§6[§3FK§6]§8 » §7Mode de jeu définit sur §2Survie §7!");
        } 
        
        if (gm.equalsIgnoreCase("1")) {
        	player.setGameMode(GameMode.CREATIVE);
            player.sendMessage("§6[§3FK§6]§8 » §7Mode de jeu définit sur §6Créatif §7!");
        } 
        
        if (gm.equalsIgnoreCase("2")) {
        	player.setGameMode(GameMode.ADVENTURE);
            player.sendMessage("§6[§3FK§6]§8 » §7Mode de jeu définit sur §aAventure §7!");
        } 
        
        if (gm.equalsIgnoreCase("3")) {
        	player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("§6[§3FK§6]§8 » §7Mode de jeu définit sur §5Spectateur §7!");
        } 
        
        if (!gm.equalsIgnoreCase("0") && !gm.equalsIgnoreCase("1") && !gm.equalsIgnoreCase("2") && !gm.equalsIgnoreCase("3")) {
            player.sendMessage("§6[§3FK§6]§8 » §cErreur : choisit entre /gm [§20 §c| §61 §c| §a2 §c| §53§c]");
        }


        return true;
    }
}